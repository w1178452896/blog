package com.taylorsfan.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.repository.UserMapper;
import com.taylorsfan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author momo
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean judgeHaveUserOrNot(String username) {
        return userMapper.selectOneByUsername(username) != null;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.selectOneByUserNameAndPassword(username, password);
    }

    @Override
    public boolean resetPassword(int id) {
        return userMapper.updateByPrimaryKeyPassword2Null(id) != 0;
    }

    /**
     * 显示用户
     * 所有的
     * 粉丝
     * 关注的人
     */
    @Override
    public List<User> showAll(Map<String, Integer> map) {
        List<User> userList;
        PageHelper.startPage(map.get("pageNum"), map.get("pageSize"));
        //粉丝列表
        if (map.containsKey("fan") && !map.containsKey("focus")) {
            userList = userMapper.selectAllByFanId(map.get("fanId"));
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            return pageInfo.getList();
        }
        //关注列表
        else if (!map.containsKey("fan") && map.containsKey("focus")) {
            userList = userMapper.selectAllByFocusId(map.get("focus"));
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            return pageInfo.getList();
        }
        //所有的
        else if (!map.containsKey("fan") && !map.containsKey("focus")) {
            userList = userMapper.selectAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            return pageInfo.getList();
        }
        return null;
    }

    @Override
    public User showUserByBlogId(int blogId) {
        return userMapper.selectOneByBlogId(blogId);
    }

    @Override
    public User showUserByCommentId(int commentId) {
        return userMapper.selectOneByCommentId(commentId);
    }

    @Override
    public User showOne(int id) {
        return userMapper.selectOneByPrimaryKey(id);
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user) != 0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user) != 0;
    }

    @Override
    public boolean delete(int id) {
        return userMapper.deleteByPrimaryKey(id) != 0;
    }

}
