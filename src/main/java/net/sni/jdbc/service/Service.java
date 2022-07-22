package net.sni.jdbc.service;

import java.util.Optional;

public interface Service<T, ID> {
    T save(T entity);

    Iterable<T> saveAll(Iterable<T> entities);

    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    boolean delete(ID id);

    boolean deleteAll();
}
