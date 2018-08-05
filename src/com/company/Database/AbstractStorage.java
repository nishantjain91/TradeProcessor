package com.company.Database;


public interface AbstractStorage<T> extends Query {
    boolean add(T object);
}
