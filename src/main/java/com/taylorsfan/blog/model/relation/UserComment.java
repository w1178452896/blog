package com.taylorsfan.blog.model.relation;

/**
 * @author taylorsfan
 */
public class UserComment {
    private Integer id;

    private Integer userId;

    private Integer commentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public UserComment(Integer id, Integer userId, Integer commentId) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
    }
}