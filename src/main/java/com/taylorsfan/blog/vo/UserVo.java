package com.taylorsfan.blog.vo;

import com.taylorsfan.blog.model.*;

import java.util.List;


/**
 * @author taylorsfan
 */

public class UserVo {
    private User user;
    private Integer focusCount;
    private Integer fanCount;
    private Integer blogCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getFanCount() {
        return fanCount;
    }

    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

}
