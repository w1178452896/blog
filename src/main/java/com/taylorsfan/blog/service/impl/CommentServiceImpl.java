package com.taylorsfan.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taylorsfan.blog.model.Comment;
import com.taylorsfan.blog.repository.CommentMapper;
import com.taylorsfan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> showAll(Map<String, Integer> map) {
        List<Comment> commentList;
        PageHelper.startPage(map.get("pageNum"), map.get("pageSize"));
        // 用户界面显示|后台根据用户查询
        if (map.containsKey("userId") && !map.containsKey("blogId") && !map.containsKey("status")) {
            commentList = commentMapper.selectAllByUserId(map.get("userId"));
            PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
            return pageInfo.getList();
        }
        //文章界面显示|后台根据文章查询
        else if (!map.containsKey("userId") && map.containsKey("blogId") && !map.containsKey("status")) {
            commentList = commentMapper.selectAllByBlogId(map.get("blogId"));
            PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
            return pageInfo.getList();
        }
        //前台审核 |后台根据状态查询
        else if (!map.containsKey("userId") && !map.containsKey("blogId") && map.containsKey("status")) {
            commentList = commentMapper.selectAllByStatus(map.get("status"));
            PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
            return pageInfo.getList();
        }
        //后台查询
        else if (!map.containsKey("userId") && !map.containsKey("blogId") && !map.containsKey("status")) {
            commentList = commentMapper.selectAll();
            PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
            return pageInfo.getList();
        }

        return null;
    }

    @Override
    public boolean update(Comment comment) {
        return false;
    }

    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment) != 0;
    }

    @Override
    public boolean delete(int id) {
        return commentMapper.updateByCommentId(id) != 0;
    }

    @Override
    public Comment showOne(int id) {
        return null;
    }

    @Override
    public boolean deleteByUserId(int userId) {
        return commentMapper.updateByUserId(userId) != 0;
    }

    @Override
    public boolean deleteByBlogId(int blogId) {
        return commentMapper.deleteByBlogId(blogId) != 0;
    }

    @Override
    public boolean updateByChecker(int commentId) {
        return commentMapper.update(commentId) != 0;
    }

    @Override
    public boolean deleteByCommentId(int commentId) {
        return commentMapper.updateByCommentId(commentId) != 0;
    }

}
