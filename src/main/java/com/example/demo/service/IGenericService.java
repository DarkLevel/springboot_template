package com.example.demo.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;

public interface IGenericService<T extends GenericModel, I extends Serializable> {

	public Optional<T> get(I i) throws GenericException;

	public Collection<T> get(Collection<I> lI) throws GenericException;

	public T create(T t) throws GenericException;

	public Collection<T> create(Collection<T> lT) throws GenericException;

	public T update(I i, T t) throws GenericException;

	public Collection<T> disable(Collection<I> lI) throws GenericException;

	public Collection<T> enable(Collection<I> lI) throws GenericException;

	public int delete(I i) throws GenericException;

	public int delete(Collection<I> lI) throws GenericException;

}
