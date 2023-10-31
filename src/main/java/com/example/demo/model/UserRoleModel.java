package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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

  public UserRoleModel(UserModel userModel, Long roleId) {
    this.userModel = userModel;
    this.roleModel = new RoleModel(roleId);
  }

}
