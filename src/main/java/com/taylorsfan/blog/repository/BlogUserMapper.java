package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.BlogUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface BlogUserMapper {

    int insert(@Param("blogUser") BlogUser blogUser);

    int countUser(@Param("blogId") int blogId);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByBlogId(@Param("blogId") int blogId);

}