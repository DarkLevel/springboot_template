package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "user_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
public class UserRoleModel extends GenericModel {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private UserModel userModel;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false, updatable = false)
  private RoleModel roleModel;

}
