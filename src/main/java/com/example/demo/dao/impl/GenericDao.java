package com.example.demo.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.example.demo.dao.IGenericDao;
import com.example.demo.model.GenericModel;
import com.example.demo.repository.IGenericRepository;

public abstract class GenericDao<T extends GenericModel, I extends Serializable>
    implements IGenericDao<T, I> {

  private final IGenericRepository<T, I> genericRepository;

  protected GenericDao(IGenericRepository<T, I> genericRepository) {
    this.genericRepository = genericRepository;
  }

  @Override
  public Optional<T> get(@NonNull I i) {
    return genericRepository.findById(i);
  }

  @Override
  public Collection<T> get(Collection<I> lI) {
    return lI != null && !lI.isEmpty() ? genericRepository.findAllById(lI) : genericRepository.findAll();
  }

  @Override
  public T save(@NonNull T t) {
    return genericRepository.save(t);
  }

  @Override
  public Collection<T> save(@NonNull Collection<T> lT) {
    return genericRepository.saveAll(lT);
  }

  @Override
  public void delete(@NonNull I i) {
    genericRepository.deleteById(i);
  }

  @Override
  public void delete(@NonNull Collection<I> lI) {
    genericRepository.deleteAllById(lI);
  }

}
