package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.Role;
import com.taylorsfan.blog.model.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface RoleMapper {

    List<Role> selectAll();

    Role selectOneByPrimaryKey(@Param("id") int id);

    int deleteByPrimaryKey(@Param("id") int id);

    int insert(@Param("role") Role role);

    int updateByPrimaryKey(@Param("role") Role role);

    List<Role> selectAllByUserId(@Param("userId") int userId);

}