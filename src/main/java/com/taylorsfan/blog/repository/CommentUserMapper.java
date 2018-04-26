package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.CommentUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface CommentUserMapper {

    int insert(@Param("commentUser") CommentUser commentUser);

    int countUser(@Param("id") int id);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByCommentId(@Param("commentId") int commentId);

}