package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.UserComment;
import com.taylorsfan.blog.repository.UserCommentMapper;
import com.taylorsfan.blog.service.relation.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class UserCommentServiceImpl implements UserCommentService {
    private final UserCommentMapper userCommentMapper;

    @Autowired
    public UserCommentServiceImpl(UserCommentMapper userCommentMapper) {
        this.userCommentMapper = userCommentMapper;
    }

    @Override
    public int count(int id) {
        return userCommentMapper.countComment(id);
    }

    @Override
    public boolean insert(UserComment userComment) {
        return userCommentMapper.insert(userComment) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return userCommentMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return userCommentMapper.deleteByCommentId(id) != 0;
    }
}
