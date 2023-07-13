package com.example.demo.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.GenericModel;

public interface IGenericDao<T extends GenericModel, I extends Serializable> {

  public Optional<T> get(I i);

  public Collection<T> get(Collection<I> lI);

  public T save(T t);

  public Collection<T> save(Collection<T> lT);

  public void delete(I i);

  public void delete(Collection<I> lI);

}
