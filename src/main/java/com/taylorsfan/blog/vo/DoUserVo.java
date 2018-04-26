package com.taylorsfan.blog.vo;

import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.model.Role;
import com.taylorsfan.blog.model.User;

import java.util.List;

/**
 * @author tianle
 */
public class DoUserVo {
    private User user;
    private List<Role> roleList;
    private List<Permission> permissionList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
