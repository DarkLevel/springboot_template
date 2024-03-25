package com.example.demo.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.example.demo.model.GenericModel;

public interface IGenericDao<T extends GenericModel, I extends Serializable> {

  public Optional<T> get(@NonNull I i);

  public Collection<T> get(Collection<I> lI);

  public T save(@NonNull T t);

  public Collection<T> save(@NonNull Collection<T> lT);

  public void delete(@NonNull I i);

  public void delete(@NonNull Collection<I> lI);

}
