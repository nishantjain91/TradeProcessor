package com.company.Database;


public interface AbstractStorage<T> extends Query {
    AbstractStorage getInstance();
    boolean add(T object);
}
