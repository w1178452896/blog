package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.UserBlog;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface UserBlogMapper {

    int countBlog(@Param("userId") int userId);

    int insert(@Param("userBlog") UserBlog userBlog);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByBlogId(@Param("blogId") int blogId);
}