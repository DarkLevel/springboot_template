package com.example.demo.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "refresh_token")
public class RefreshTokenModel extends GenericModel {

  @Column(nullable = false, updatable = false, unique = true)
  private String refreshToken;

  @CreationTimestamp
  @JsonProperty(access = Access.READ_ONLY)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Europe/Madrid")
  @Column(nullable = false, updatable = false)
  private Instant issuedAt;

  @JsonProperty(access = Access.READ_ONLY)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Europe/Madrid")
  @Column(nullable = false, updatable = false)
  private Instant expiration;

  @OneToOne
  @JsonProperty(access = Access.WRITE_ONLY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private UserModel userModel;

  public RefreshTokenModel(String refreshToken, Instant expiration, UserModel userModel) {
    setEnabled(true);
    this.refreshToken = refreshToken;
    this.expiration = expiration;
    this.userModel = userModel;
  }

}
