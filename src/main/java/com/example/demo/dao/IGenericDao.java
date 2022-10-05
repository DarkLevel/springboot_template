package com.example.demo.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.GenericModel;

public interface IGenericDao<T extends GenericModel, ID extends Serializable> {

    public Optional<T> get(ID id);

    public Collection<T> get(Collection<ID> lId);

    public T save(T t);

    public Collection<T> save(Collection<T> lT);

    public void delete(ID id);

    public void delete(Collection<ID> lId);

}
