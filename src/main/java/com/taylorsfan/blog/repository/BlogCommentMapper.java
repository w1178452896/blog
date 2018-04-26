package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.BlogComment;
import org.apache.ibatis.annotations.Param;


/**
 * @author taylorsfan
 */
public interface BlogCommentMapper {


    int insert(@Param("blogComment") BlogComment blogComment);

    int countComment(@Param("id") int id);

    int deleteByBlogId(@Param("blogId") int blogId);

}