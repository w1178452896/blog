package com.taylorsfan.blog.vo;

import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.model.Role;

import java.util.List;

/**
 * @author tianle
 */
public class RoleVo {

    private Role role;
    private List<Permission> permissionList;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
