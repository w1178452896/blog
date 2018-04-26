package com.taylorsfan.blog.repository;

import com.github.pagehelper.PageInfo;
import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.model.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface PermissionMapper {

    List<Permission> selectAll();

    Permission selectOneByPrimaryKey(@Param("id") int id);

    int deleteByPrimaryKey(@Param("id") int id);

    int insert(@Param("permission") Permission permission);

    int updateByPrimaryKey(@Param("permission") Permission permission);

    List<Permission> selectAllByRoleId(@Param("roleId") int permissionId);

    List<Permission> selectAllByUserId(@Param("userId") int userId);
}