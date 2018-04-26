package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface CommentMapper {

    int insert(@Param("comment") Comment comment);

    /**
     * 根据文章id删除
     */
    int deleteByBlogId(@Param("blogId") int blogId);

    int update(@Param("id") int id);

    /**
     * 用户注销，根据用户id删除
     */
    int updateByUserId(@Param("userId") int userId);

    /**
     * 用户删除自己的评论
     */
    int updateByCommentId(@Param("commentId") int commentId);

    List<Comment> selectAll();

    List<Comment> selectAllByStatus(@Param("status") int status);

    /**
     * 根据用户id查询
     */
    List<Comment> selectAllByUserId(@Param("userId") int userId);

    /**
     * 根据文章id查询 时间倒叙排列
     */
    List<Comment> selectAllByBlogId(@Param("blogId") int blogId);

    Comment selectOneByPrimaryKey(@Param("id") int id);


}