package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.SortBlog;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface SortBlogMapper {

    int countBlog(@Param("sortId") int sortId);

    int insert(@Param("sortBlog") SortBlog sortBlog);

    int deleteByBlogId(@Param("blogId") int blogId);

    int deleteBySortId(@Param("sortId") int sortId);

}
