package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.example.demo.dao.IGenericDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;
import com.example.demo.service.IGenericService;

import jakarta.transaction.Transactional;

public abstract class GenericService<T extends GenericModel, I extends Serializable>
    implements IGenericService<T, I> {

  private final IGenericDao<T, I> genericDao;

  protected GenericService(IGenericDao<T, I> genericDao) {
    this.genericDao = genericDao;
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Optional<T> get(@NonNull I i) throws GenericException {
    Optional<T> resultObject;

    try {
      beforeGet(i);
      resultObject = genericDao.get(i);
      afterGet(resultObject.isPresent() ? resultObject.get() : null);
    } catch (GenericException e) {
      throw new GenericException(e.getMessage(), e, e.getCode());
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }

    return resultObject;
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<T> get(Collection<I> lI) throws GenericException {
    try {
      return genericDao.get(lI);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public T create(@NonNull T t) throws GenericException {
    try {
      return genericDao.save(t);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<T> create(Collection<T> lT) throws GenericException {
    try {
      lT.removeAll(Collections.singleton(null));
      return genericDao.save(lT);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public T update(I i, T t) throws GenericException {
    try {
      t.setId((Long) i);
      return genericDao.save(t);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<T> disable(Collection<I> lI) throws GenericException {
    try {
      return changeStatus(lI, false);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<T> enable(Collection<I> lI) throws GenericException {
    try {
      return changeStatus(lI, true);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public int delete(@NonNull I i) throws GenericException {
    try {
      genericDao.delete(i);
      return 1;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public int delete(Collection<I> lI) throws GenericException {
    try {
      lI.removeAll(Collections.singleton(null));
      genericDao.delete(lI);
      return lI.size();
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  private Collection<T> changeStatus(Collection<I> lI, boolean enabled) throws GenericException {
    try {
      Collection<T> lT = genericDao.get(lI);

      lT.stream().forEach(el -> el.setEnabled(enabled));

      return genericDao.save(lT);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  public void beforeGet(I i) throws GenericException {
    // Override when implementing
  }

  @Override
  public void afterGet(T t) throws GenericException {
    // Override when implementing
  }

}
