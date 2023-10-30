package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable()).authorizeRequests(
        requests -> requests.mvcMatchers(HttpMethod.GET).hasAnyRole("ADMIN", "USER").mvcMatchers(HttpMethod.POST)
            .hasAnyRole("ADMIN").mvcMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
            .mvcMatchers(HttpMethod.PATCH).hasAnyRole("ADMIN").mvcMatchers(HttpMethod.DELETE)
            .hasAnyRole("ADMIN").anyRequest().authenticated())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
  }

  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().mvcMatchers("/configuration/**", "/swagger-ui/**", "/docs/**");
  }

}
