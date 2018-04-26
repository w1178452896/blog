package com.taylorsfan.blog.vo;


import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.model.Comment;
import com.taylorsfan.blog.model.User;


/**
 * @author momo
 */
public class CommentVo {
    /**
     *
     */
    private int blogId;
    /**
     * 评论
     */
    private Comment comment;
    /**
     * 对应用户
     */
    private User commentUser;
    /**
     * 评论的对象
     */
    private User beCommentedUser;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public User getBeCommentedUser() {
        return beCommentedUser;
    }

    public void setBeCommentedUser(User beCommentedUser) {
        this.beCommentedUser = beCommentedUser;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

}
