package com.taylorsfan.blog.vo;

import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.model.Sort;
import com.taylorsfan.blog.model.User;

/**
 * @author wang
 */
public class BlogVo {
    /**
     * 博客
     */
    private Blog blog;
    /**
     * 对应一个用户
     */
    private User user;
    /**
     * 分类
     */
    private Sort sort;
    /**
     * 点赞总数
     */
    private Integer userCount;

    private Integer commentCount;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
