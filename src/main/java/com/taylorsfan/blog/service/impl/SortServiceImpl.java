package com.taylorsfan.blog.service.impl;

import com.taylorsfan.blog.model.Sort;
import com.taylorsfan.blog.repository.SortMapper;
import com.taylorsfan.blog.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
@Service
public class SortServiceImpl implements SortService {

    private final SortMapper sortMapper;

    @Autowired
    public SortServiceImpl(SortMapper sortMapper) {
        this.sortMapper = sortMapper;
    }

    @Override
    public List<Sort> showAll(Map<String, Integer> map) {
        return sortMapper.selectAll();
    }

    @Override
    public boolean update(Sort sort) {
        return sortMapper.updateByPrimaryKey(sort) != 0;
    }

    @Override
    public boolean delete(int id) {
        return sortMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Sort showOne(int id) {
        return sortMapper.selectOneByPrimaryKey(id);
    }

    @Override
    public boolean insert(Sort sort) {
        return sortMapper.insert(sort) != 0;
    }

    @Override
    public Sort showSortByBlogId(int blogId) {
        return sortMapper.selectOneByBlogId(blogId);
    }

}
