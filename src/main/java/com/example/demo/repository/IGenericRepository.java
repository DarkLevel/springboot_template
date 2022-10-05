package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.model.GenericModel;

@NoRepositoryBean
public interface IGenericRepository<T extends GenericModel, ID extends Serializable> extends JpaRepository<T, ID> {

}
