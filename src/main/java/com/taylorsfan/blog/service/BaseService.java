package com.taylorsfan.blog.service;

import java.util.List;
import java.util.Map;

/**
 * @author tianle
 */
public interface BaseService<T> {

    boolean insert(T t);

    boolean delete(int id);

    T showOne(int id);

    List<T> showAll(Map<String, Integer> map);

    boolean update(T t);
}
