package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.RolePermission;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface RolePermissionMapper {

    int insert(@Param("rolePermission") RolePermission rolePermission);

    int countPermission(@Param("roleId") int roleId);

    int deleteByRoleId(@Param("roleId") int roleId);

    int deleteByPermissionId(@Param("permissionId") int permissionId);
}