package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.SortBlog;
import com.taylorsfan.blog.repository.SortBlogMapper;
import com.taylorsfan.blog.service.relation.SortBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class SortBlogServiceImpl implements SortBlogService {

    private final SortBlogMapper sortBlogMapper;

    @Autowired
    public SortBlogServiceImpl(SortBlogMapper sortBlogMapper) {
        this.sortBlogMapper = sortBlogMapper;
    }

    @Override
    public int count(int id) {
        return sortBlogMapper.countBlog(id);
    }

    @Override
    public boolean insert(SortBlog sortBlog) {
        return sortBlogMapper.insert(sortBlog) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return sortBlogMapper.deleteBySortId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return sortBlogMapper.deleteByBlogId(id) != 0;
    }
}
