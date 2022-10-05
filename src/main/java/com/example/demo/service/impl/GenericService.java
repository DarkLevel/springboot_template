package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.IGenericDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;
import com.example.demo.service.IGenericService;

public abstract class GenericService<T extends GenericModel, ID extends Serializable>
        implements IGenericService<T, ID> {

    @Autowired
    private IGenericDao<T, ID> genericDao;

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Optional<T> get(ID id) throws GenericException {
        try {
            return genericDao.get(id);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> get(Collection<ID> lId) throws GenericException {
        try {
            return genericDao.get(lId);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public T create(T t) throws GenericException {
        try {
            return genericDao.save(t);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> create(Collection<T> lT) throws GenericException {
        try {
            lT.removeAll(Collections.singleton(null));
            return genericDao.save(lT);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public T update(ID id, T t) throws GenericException {
        try {
            t.setId((Long) id);
            return genericDao.save(t);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> disable(Collection<ID> lId) throws GenericException {
        try {
            return changeStatus(lId, true);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> enable(Collection<ID> lId) throws GenericException {
        try {
            return changeStatus(lId, false);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public int delete(ID id) throws GenericException {
        try {
            genericDao.delete(id);
            return 1;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public int delete(Collection<ID> lId) throws GenericException {
        try {
            lId.removeAll(Collections.singleton(null));
            genericDao.delete(lId);
            return lId.size();
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    private Collection<T> changeStatus(Collection<ID> lId, boolean disabled) {
        Collection<T> lT = new ArrayList<>();

        for (ID id : lId) {
            lT.add(genericDao.get(id).get());
        }

        lT.stream().forEach(el -> el.setDisabled(disabled));

        return genericDao.save(lT);
    }

}
