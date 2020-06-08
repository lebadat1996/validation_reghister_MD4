package com.codegym.service;

public interface IService<T> {
    Iterable<T> findAll();

    T save(T model);

    T getUserById(Long id);

    void update(T model);

    void remove(Long id);
}
