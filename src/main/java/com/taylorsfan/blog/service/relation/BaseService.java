package com.taylorsfan.blog.service.relation;

/**
 * @author tianle
 */
public interface BaseService<T> {

    int count(int id);

    boolean insert(T t);

    boolean deleteByOneId(int id);

    boolean deleteByMoreId(int id);
}
