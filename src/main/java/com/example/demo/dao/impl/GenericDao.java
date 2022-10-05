package com.example.demo.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.IGenericDao;
import com.example.demo.model.GenericModel;
import com.example.demo.repository.IGenericRepository;

public abstract class GenericDao<T extends GenericModel, ID extends Serializable>
        implements IGenericDao<T, ID> {

    @Autowired
    private IGenericRepository<T, ID> genericRepository;

    @Override
    public Optional<T> get(ID id) {
        return genericRepository.findById(id);
    }

    @Override
    public Collection<T> get(Collection<ID> lId) {
        return lId != null && !lId.isEmpty() ? genericRepository.findAllById(lId) : genericRepository.findAll();
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
    public void delete(ID id) {
        genericRepository.deleteById(id);
    }

    @Override
    public void delete(Collection<ID> lId) {
        for (ID id : lId) {
            genericRepository.deleteById(id);
        }
    }

}
