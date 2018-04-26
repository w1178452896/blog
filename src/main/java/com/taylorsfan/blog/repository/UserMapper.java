package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author taylorsfan
 */
public interface UserMapper {

    List<User> selectAll();

    /**
     * 根据粉丝id查找用户
     */
    List<User> selectAllByFanId(@Param("fanId") int fanId);

    /**
     * 根据关注者id查用户
     */
    List<User> selectAllByFocusId(@Param("focusId") int focusId);

    User selectOneByPrimaryKey(@Param("id") int id);

    int deleteByPrimaryKey(@Param("id") int id);

    int insert(@Param("user") User user);

    int updateByPrimaryKey(@Param("user") User user);

    /**
     * 根据文章id查用户
     */
    User selectOneByBlogId(@Param("blogId") int blogId);

    /**
     * 根据评论id查用户
     */
    User selectOneByCommentId(@Param("commentId") int commentId);

    /**
     * 重置密码
     */
    int updateByPrimaryKeyPassword2Null(@Param("id") int id);

    /**
     * 根据用户名查询用户
     */
    User selectOneByUsername(@Param("username") String username);

    /**
     * FIXME
     * 根据用户名和密码查询
     */
    User selectOneByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

}