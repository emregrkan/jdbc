package net.sni.jdbc.service;

import java.util.Optional;

public interface Service<T, ID> {
    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    boolean delete(ID id);

    boolean deleteAll();
}
