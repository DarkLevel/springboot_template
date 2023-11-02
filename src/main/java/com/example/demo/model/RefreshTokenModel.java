package com.example.demo.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @Column(unique = true, nullable = false)
  private Long id;

  @JsonIgnore
  @Column(nullable = false, columnDefinition = "boolean default true")
  private boolean enabled;

  @CreatedBy
  @JsonIgnore
  @Column(length = 20, nullable = false, updatable = false)
  private String createdBy;

  @CreationTimestamp
  @JsonIgnore
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
  @Column(nullable = false, updatable = false)
  private Instant createdDate;

  @LastModifiedBy
  @JsonIgnore
  @Column(length = 20, insertable = false)
  private String modifiedBy;

  @UpdateTimestamp
  @JsonIgnore
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
  @Column(insertable = false)
  private Instant modifiedDate;

  @Column(nullable = false, updatable = false, unique = true)
  private String token;

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

  public RefreshTokenModel(String token, Instant expiration, UserModel userModel) {
    setEnabled(true);
    this.token = token;
    this.expiration = expiration;
    this.userModel = userModel;
  }

}
