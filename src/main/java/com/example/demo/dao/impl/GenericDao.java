package com.example.demo.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.example.demo.dao.IGenericDao;
import com.example.demo.model.GenericModel;
import com.example.demo.repository.IGenericRepository;

public abstract class GenericDao<T extends GenericModel, I extends Serializable>
    implements IGenericDao<T, I> {

  private final IGenericRepository<T, I> genericRepository;

  public GenericDao(IGenericRepository<T, I> genericRepository) {
    this.genericRepository = genericRepository;
  }

  @Override
  public Optional<T> get(I i) {
    return genericRepository.findById(i);
  }

  @Override
  public Collection<T> get(Collection<I> lI) {
    return lI != null && !lI.isEmpty() ? genericRepository.findAllById(lI) : genericRepository.findAll();
  }

  @Override
  public T save(T t) {
    return genericRepository.save(t);
  }

  @Override
  public Collection<T> save(Collection<T> lT) {
    return genericRepository.saveAll(lT);
  }

  @Override
  public void delete(I i) {
    genericRepository.deleteById(i);
  }

  @Override
  public void delete(Collection<I> lI) {
    for (I i : lI) {
      genericRepository.deleteById(i);
    }
  }

}
