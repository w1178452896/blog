package com.taylorsfan.blog.model.relation;

/**
 * @author taylorsfan
 */
public class UserFocus {
    private Integer id;

    private Integer userId;

    private Integer focusId;

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

    public Integer getFocusId() {
        return focusId;
    }

    public void setFocusId(Integer focusId) {
        this.focusId = focusId;
    }
}