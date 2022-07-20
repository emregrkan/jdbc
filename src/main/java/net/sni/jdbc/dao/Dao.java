package net.sni.jdbc.dao;

import java.util.Optional;

public interface Dao<T, ID> {

    void init();

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    boolean delete(T entity);

    boolean deleteAll();
}
