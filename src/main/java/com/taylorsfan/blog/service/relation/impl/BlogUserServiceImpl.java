package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.BlogUser;
import com.taylorsfan.blog.repository.BlogUserMapper;
import com.taylorsfan.blog.service.relation.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class BlogUserServiceImpl implements BlogUserService {
    private final BlogUserMapper blogUserMapper;

    @Autowired
    public BlogUserServiceImpl(BlogUserMapper blogUserMapper) {
        this.blogUserMapper = blogUserMapper;
    }

    @Override
    public int count(int id) {
        return blogUserMapper.countUser(id);
    }

    @Override
    public boolean insert(BlogUser blogUser) {
        return blogUserMapper.insert(blogUser) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return blogUserMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return blogUserMapper.deleteByBlogId(id) != 0;
    }
}
