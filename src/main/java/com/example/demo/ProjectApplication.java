package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProjectApplication.class);

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Value("${password.example}")
  private String password;

  public static void main(String[] args) {
    SpringApplication.run(ProjectApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String generatedPassword = passwordEncoder.encode(password);
    if (!StringUtils.isEmpty(generatedPassword)) {
      log.info(generatedPassword);
    }
  }

}
