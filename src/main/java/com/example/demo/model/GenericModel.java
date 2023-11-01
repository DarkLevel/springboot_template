package com.example.demo.model;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private boolean enabled;

  @CreatedBy
  @Column(name = "created_by", length = 20, nullable = false, updatable = false)
  private String createdBy;

  @CreationTimestamp
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
  @Column(name = "created_date", nullable = false, updatable = false)
  private Instant createdDate;

  @LastModifiedBy
  @Column(name = "modified_by", length = 20, insertable = false)
  private String modifiedBy;

  @UpdateTimestamp
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Europe/Madrid")
  @Column(name = "modified_date", insertable = false)
  private Instant modifiedDate;

}
