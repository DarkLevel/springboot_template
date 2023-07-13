package com.example.demo.controller.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.controller.IGenericController;
import com.example.demo.exception.GenericException;
import com.example.demo.model.GenericModel;
import com.example.demo.service.impl.GenericService;
import com.example.demo.utils.ResponseObject;
import com.example.demo.utils.ResponseUtils;

@CrossOrigin
public abstract class GenericController<T extends GenericModel, S extends GenericService<T, Long>>
    implements IGenericController<T, Long> {

  @Autowired
  protected S service;

  @Override
  public ResponseEntity<ResponseObject> get(Long id) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.get(id));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> get(Collection<Long> id) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.get(id));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> create(T t) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.create(t));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> create(Collection<T> lT) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.create(lT));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> update(Long id, T t) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.update(id, t));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> disable(Collection<Long> lId) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.disable(lId));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> enable(Collection<Long> lId) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.enable(lId));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> delete(Long id) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.delete(id));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> delete(Collection<Long> lId) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.delete(lId));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

}
