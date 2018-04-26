package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.BlogComment;
import com.taylorsfan.blog.repository.BlogCommentMapper;
import com.taylorsfan.blog.service.relation.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    private final BlogCommentMapper blogCommentMapper;

    @Autowired
    public BlogCommentServiceImpl(BlogCommentMapper blogCommentMapper) {
        this.blogCommentMapper = blogCommentMapper;
    }

    @Override
    public int count(int id) {
        return blogCommentMapper.countComment(id);
    }

    @Override
    public boolean insert(BlogComment blogComment) {
        return blogCommentMapper.insert(blogComment) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return blogCommentMapper.deleteByBlogId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return false;
    }
}
