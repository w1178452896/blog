package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.UserComment;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface UserCommentMapper {

    int countComment(@Param("userId") int userId);

    int insert(@Param("userComment") UserComment userComment);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByCommentId(@Param("commentId") int commentId);
}