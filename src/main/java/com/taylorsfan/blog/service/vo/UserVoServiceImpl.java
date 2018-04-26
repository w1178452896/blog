package com.taylorsfan.blog.service.vo;

import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.service.UserService;
import com.taylorsfan.blog.service.relation.*;
import com.taylorsfan.blog.util.MapUtil;
import com.taylorsfan.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tianle
 */
@Service
public class UserVoServiceImpl implements UserVoService {
    private final UserService userService;
    private final UserBlogService userBlogService;
    private final UserFanService userFanService;
    private final UserFocusService userFocusService;

    @Autowired
    public UserVoServiceImpl(UserService userService, UserBlogService userBlogService, UserCommentService userCommentService, UserFanService userFanService, UserFocusService userFocusService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userBlogService = userBlogService;
        this.userFanService = userFanService;
        this.userFocusService = userFocusService;
    }

    @Override
    public UserVo userVo(int userId, boolean userExists) {
        UserVo userVo = new UserVo();
        if (userExists) {
            userVo.setBlogCount(userBlogService.count(userId));
            userVo.setFanCount(userFanService.count(userId));
            userVo.setFocusCount(userFocusService.count(userId));
            return userVo;
        } else {
            userVo.setUser(userService.showOne(userId));
            userVo.setBlogCount(userBlogService.count(userId));
            userVo.setFanCount(userFanService.count(userId));
            userVo.setFocusCount(userFocusService.count(userId));
            return userVo;
        }
    }

    @Override
    public List<UserVo> userVoList(int userId, int fan, int focus, int pageNum, int pageSize) {
        Map<String, Integer> map = MapUtil.int2map(pageNum, pageSize);
        map.put("userId", userId);
        if (fan != 0 && focus == 0) {
            map.put("fan", fan);
            return map2userVoList(map, userId);
        } else if (fan == 0 && focus != 0) {
            map.put("focus", focus);
            return map2userVoList(map, userId);
        }
        return null;
    }

    private List<UserVo> map2userVoList(Map<String, Integer> map, int userId) {
        List<UserVo> userVoList = new ArrayList<>();
        List<User> userList = userService.showAll(map);
        for (User user : userList) {
            UserVo userVo = new UserVo();
            userVo.setUser(userService.showOne(userId));
            userVo.setBlogCount(userBlogService.count(userId));
            userVo.setFanCount(userFanService.count(userId));
            userVo.setFocusCount(userFocusService.count(userId));
            userVoList.add(userVo);
        }
        return userVoList;
    }

}
