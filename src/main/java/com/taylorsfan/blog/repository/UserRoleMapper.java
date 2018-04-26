package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface UserRoleMapper {

    int countRole(@Param("userId") int userId);

    int insert(@Param("userRole") UserRole userRole);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByRoleId(@Param("roleId") int roleId);
}