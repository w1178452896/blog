package com.taylorsfan.blog.service;

import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author momo
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据文章id查询user
     */
    User showUserByBlogId(int blogId);

    /**
     * 根据评论id查询user
     */
    User showUserByCommentId(int commentId);

    /**
     * 根据username判断是否在user表中
     */
    boolean judgeHaveUserOrNot(String username);

    /**
     * 登陆
     */
    User login(String username, String password);

    /**
     * 重置密码
     */
    boolean resetPassword(int userId);

}
