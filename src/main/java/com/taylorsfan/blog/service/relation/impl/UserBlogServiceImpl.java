package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.UserBlog;
import com.taylorsfan.blog.repository.UserBlogMapper;
import com.taylorsfan.blog.service.relation.UserBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class UserBlogServiceImpl implements UserBlogService {
    private final UserBlogMapper userBlogMapper;

    @Autowired
    public UserBlogServiceImpl(UserBlogMapper userBlogMapper) {
        this.userBlogMapper = userBlogMapper;
    }

    @Override
    public int count(int id) {
        return userBlogMapper.countBlog(id);
    }

    @Override
    public boolean insert(UserBlog userBlog) {
        return userBlogMapper.insert(userBlog) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return userBlogMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return userBlogMapper.deleteByBlogId(id) != 0;
    }
}
