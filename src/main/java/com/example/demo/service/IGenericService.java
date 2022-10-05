package com.example.demo.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;

public interface IGenericService<T extends GenericModel, ID extends Serializable> {

	public Optional<T> get(ID id) throws GenericException;

	public Collection<T> get(Collection<ID> lId) throws GenericException;

	public T create(T t) throws GenericException;

	public Collection<T> create(Collection<T> lT) throws GenericException;

	public T update(ID id, T t) throws GenericException;

	public Collection<T> disable(Collection<ID> lId) throws GenericException;

	public Collection<T> enable(Collection<ID> lId) throws GenericException;

	public int delete(ID id) throws GenericException;

	public int delete(Collection<ID> lId) throws GenericException;

}
