package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface SortMapper {

    List<Sort> selectAll();

    Sort selectOneByPrimaryKey(@Param("id") int id);

    int deleteByPrimaryKey(@Param("id") int id);

    int insert(@Param("sort") Sort sort);

    int updateByPrimaryKey(@Param("sort") Sort sort);

    Sort selectOneByBlogId(@Param("blogId") int blogId);
}