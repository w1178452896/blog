package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.CommentUser;
import com.taylorsfan.blog.repository.CommentUserMapper;
import com.taylorsfan.blog.service.relation.CommentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class CommentUserServiceImpl implements CommentUserService {
    private final CommentUserMapper commentUserMapper;

    @Autowired
    public CommentUserServiceImpl(CommentUserMapper commentUserMapper) {
        this.commentUserMapper = commentUserMapper;
    }

    @Override
    public int count(int id) {
        return commentUserMapper.countUser(id);
    }

    @Override
    public boolean insert(CommentUser commentUser) {
        return commentUserMapper.insert(commentUser) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return commentUserMapper.deleteByCommentId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return commentUserMapper.deleteByUserId(id) != 0;
    }
}
