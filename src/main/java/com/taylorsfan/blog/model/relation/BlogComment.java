package com.taylorsfan.blog.model.relation;

/**
 * @author taylorsfan
 */
public class BlogComment {
    private Integer id;

    private Integer blogId;

    private Integer commentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public BlogComment(Integer id, Integer blogId, Integer commentId) {
        this.id = id;
        this.blogId = blogId;
        this.commentId = commentId;
    }
}