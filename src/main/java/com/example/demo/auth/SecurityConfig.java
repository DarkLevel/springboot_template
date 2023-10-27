package com.example.demo.auth;

import static org.springframework.security.config.Customizer.withDefaults;

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

import com.example.demo.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

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
    http.authorizeRequests(
        requests -> requests.antMatchers(HttpMethod.POST).hasAnyAuthority("admin").antMatchers(HttpMethod.PUT)
            .hasAnyAuthority("admin").antMatchers(HttpMethod.PATCH).hasAnyAuthority("admin").antMatchers(HttpMethod.DELETE)
            .hasAnyAuthority("admin").anyRequest().authenticated())
        .csrf(withDefaults())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
  }

  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/configuration/**", "/swagger-ui/**", "/docs/**");
  }

}
