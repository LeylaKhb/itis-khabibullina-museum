package itis.khabibullina.dao;

import java.util.List;

public interface Dao<T> {

    T get(String name);

    List<T> getAll();

    void save(T t);
}