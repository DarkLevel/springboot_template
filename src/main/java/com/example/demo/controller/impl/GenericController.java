package com.example.demo.controller.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.controller.IGenericController;
import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;
import com.example.demo.service.impl.GenericService;
import com.example.demo.utils.ResponseUtils;

@CrossOrigin
public abstract class GenericController<T extends GenericModel, S extends GenericService<T, Long>>
        implements IGenericController<T, Long> {

    @Autowired
    protected S service;

    @Override
    public ResponseEntity<Object> get(Long id) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.get(id));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> get(Collection<Long> id) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.get(id));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> create(T t) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.create(t));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> create(Collection<T> lT) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.create(lT));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> update(Long id, T t) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.update(id, t));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> disable(Collection<Long> lId) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.disable(lId));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> enable(Collection<Long> lId) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.enable(lId));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.delete(id));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> delete(Collection<Long> lId) {
        ResponseEntity<Object> responseEntity;

        try {
            responseEntity = ResponseUtils.getResponseEntity(service.delete(lId));
        } catch (GenericException e) {
            responseEntity = ResponseUtils.getResponseEntity(e);
        }

        return responseEntity;
    }

}
