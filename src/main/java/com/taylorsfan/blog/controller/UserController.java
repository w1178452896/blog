package com.taylorsfan.blog.controller;

import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.model.relation.UserFan;
import com.taylorsfan.blog.model.relation.UserFocus;
import com.taylorsfan.blog.model.relation.UserRole;
import com.taylorsfan.blog.service.UserService;
import com.taylorsfan.blog.service.relation.UserBlogService;
import com.taylorsfan.blog.service.relation.UserFanService;
import com.taylorsfan.blog.service.relation.UserFocusService;
import com.taylorsfan.blog.service.relation.UserRoleService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import com.taylorsfan.blog.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author taylorsfan
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final UserBlogService userBlogService;
    private final UserFanService userFanService;
    private final UserFocusService userFocusService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserBlogService userBlogService, UserFanService userFanService, UserFocusService userFocusService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userBlogService = userBlogService;
        this.userFanService = userFanService;
        this.userFocusService = userFocusService;
        this.userRoleService = userRoleService;
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    public ResultUtil register(User user, String code, HttpSession session) {
        if (code.equals(session.getAttribute("veryCode"))) {
            int id = IdUtil.createId();
            if (userService.insert(user)) {
                UserRole userRole = new UserRole(id,id,1);
                if (userRoleService.insert(userRole)) {
                    return new ResultUtil(200, "success");
                }
            }
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 注销
     */
    @RequestMapping("/logoff")
    public ResultUtil logoff(int userId) {
        if (userService.delete(userId) &&
                userRoleService.deleteByOneId(userId) &&
                userBlogService.deleteByOneId(userId) &&
                userFanService.deleteByOneId(userId) &&
                userFocusService.deleteByOneId(userId)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public ResultUtil login(String username, String password, String code, HttpSession httpSession) {
        if (code.equals(httpSession.getAttribute("veryCode"))) {
            User user = userService.login(username, password);
            if (user != null) {
                httpSession.setAttribute("user", user);
                return new ResultUtil(200, "success");
            }
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 判断是否有此用户
     */
    @RequestMapping("/judgeHaveUserOrNot")
    public ResultUtil judgeHaveUserOrNot(String username) {
        if (userService.judgeHaveUserOrNot(username)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 更新个人信息
     */

    @RequestMapping("/update")
    public ResultUtil updateUserMsg(User user) {
        if (userService.update(user)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetPassword")
    public ResultUtil resetPassword(int userId) {
        if (userService.resetPassword(userId)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 点赞，关注
     */
    @RequestMapping("/{userId}/focus")
    public ResultUtil focus(@PathVariable int userId, int focusId, String operation) {
        //关注
        if (operation.equals(StringsUtil.LIKE)) {
            UserFan userFan = new UserFan();
            UserFocus userFocus = new UserFocus();
            userFan.setFanId(userId);
            userFan.setUserId(focusId);
            userFocus.setFocusId(focusId);
            userFocus.setUserId(userId);
            if (userFanService.insert(userFan) && userFocusService.insert(userFocus)) {
                return new ResultUtil(500, "failure");
            }
            return new ResultUtil(200, "success");
        }
        //取消关注
        else if (operation.equals(StringsUtil.UNLIKE)) {
            if (userFanService.deleteByMoreId(userId) && userFocusService.deleteByMoreId(focusId)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        return new ResultUtil(500, "failure");
    }

/*
    @RequestMapping("/userTest")
    @ResponseBody
    public UserVo testShowUserVo() {
        UserVo userVo = new UserVo();
        userVo.setUser(userService.showOne(1));
        userVo.setBlogCount(userBlogService.count(1));
        userVo.setFanCount(userFanService.count(1));
        userVo.setFocusCount(userFocusService.count(1));
        return userVo;
    }*/

}
