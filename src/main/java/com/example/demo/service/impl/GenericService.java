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

public abstract class GenericService<T extends GenericModel, I extends Serializable>
        implements IGenericService<T, I> {

    @Autowired
    private IGenericDao<T, I> genericDao;

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Optional<T> get(I i) throws GenericException {
        try {
            return genericDao.get(i);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> get(Collection<I> lI) throws GenericException {
        try {
            return genericDao.get(lI);
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
    public T update(I i, T t) throws GenericException {
        try {
            t.setId((Long) i);
            return genericDao.save(t);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> disable(Collection<I> lI) throws GenericException {
        try {
            return changeStatus(lI, true);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public Collection<T> enable(Collection<I> lI) throws GenericException {
        try {
            return changeStatus(lI, false);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    @Override
    @Transactional(rollbackOn = GenericException.class)
    public int delete(I i) throws GenericException {
        try {
            genericDao.delete(i);
            return 1;
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
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
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

    private Collection<T> changeStatus(Collection<I> lI, boolean disabled) {
        Collection<T> lT = new ArrayList<>();
        Optional<T> t;

        for (I i : lI) {
            t = genericDao.get(i);
            if (t.isPresent()) {
                lT.add(t.get());
            }
        }

        lT.stream().forEach(el -> el.setDisabled(disabled));

        return genericDao.save(lT);
    }

}
