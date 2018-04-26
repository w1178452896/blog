package com.taylorsfan.blog.service;

import com.taylorsfan.blog.model.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * 根据userId删除comment
     */
    boolean deleteByUserId(int userId);

    /**
     * 根据blogId删除comment
     */
    boolean deleteByBlogId(int blogId);

    /**
     * 管理员删除comment
     */
    boolean updateByChecker(int commentId);

    boolean deleteByCommentId(int commentId);
}
