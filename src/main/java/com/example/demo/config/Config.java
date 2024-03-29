package com.example.demo.config;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.dao.IUserDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.enums.OpenEndpoints;
import com.example.demo.filter.AuthFilter;
import com.example.demo.service.impl.CustomUserDetailsService;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Config {

  private final AuthFilter authFilter;
  private final IUserDao userDao;
  private final IUserRoleDao userRoleDao;
  private final String devUrl;
  private final String prodUrl;

  public Config(AuthFilter authFilter, IUserDao userDao, IUserRoleDao userRoleDao,
      @Value("${openapi.dev-url}") String devUrl,
      @Value("${openapi.prod-url}") String prodUrl) {
    this.authFilter = authFilter;
    this.userDao = userDao;
    this.userRoleDao = userRoleDao;
    this.devUrl = devUrl;
    this.prodUrl = prodUrl;
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new CustomUserDetailsService(userDao, userRoleDao);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.requestMatchers(mappings()).permitAll().anyRequest().authenticated())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  AuditorAware<String> auditorAware() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String name = authentication != null && authentication.isAuthenticated() ? authentication.getName() : null;
      return Optional.ofNullable(name);
    };
  }

  @Bean
  OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("javiroigdomenech@hotmail.com");
    contact.setName("Javier Roig Doménech");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Springboot demo")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tutorials.")
        .license(mitLicense);

    SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT")
        .scheme("bearer");

    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes("Bearer Authentication", securityScheme))
        .info(info).servers(List.of(devServer, prodServer));
  }

  private static String[] mappings() {
    return Arrays.asList(OpenEndpoints.values()).stream().map(e -> e.getValue() + "/**").toList()
        .toArray(new String[0]);
  }

}
