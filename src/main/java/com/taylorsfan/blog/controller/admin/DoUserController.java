package com.taylorsfan.blog.controller.admin;

import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.model.relation.UserRole;
import com.taylorsfan.blog.service.PermissionService;
import com.taylorsfan.blog.service.RoleService;
import com.taylorsfan.blog.service.UserService;
import com.taylorsfan.blog.service.relation.UserRoleService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.MapUtil;
import com.taylorsfan.blog.util.ResultUtil;
import com.taylorsfan.blog.vo.DoUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianle
 */
@RequestMapping("/admin/user")
@RestController
public class DoUserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final PermissionService permissionService;

    @Autowired
    public DoUserController(UserService userService, RoleService roleService,
                            UserRoleService userRoleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.permissionService = permissionService;
    }

    @RequestMapping("/all")
    public List<User> userList(int pageNum, int pageSize) {
        Map<String, Integer> map = MapUtil.int2map(pageNum, pageSize);
        return userService.showAll(map);
    }

    //
    @RequestMapping("/{userId}")
    public DoUserVo doUserVo(@PathVariable int userId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        User user = userService.showOne(userId);
        DoUserVo doUserVo = new DoUserVo();
        doUserVo.setUser(user);
        doUserVo.setRoleList(roleService.showAll(map));
        doUserVo.setPermissionList(permissionService.showAll(map));
        return doUserVo;
    }

    //    权限管理
    @RequestMapping("/{userId}/userRole")
    public ResultUtil changeRolePermission(@PathVariable int userId, int[] roleIds) {
        if (userRoleService.deleteByOneId(userId)) {
            for (int roleId : roleIds) {
                int id = IdUtil.createId();
                UserRole userRole = new UserRole(id, userId, roleId);
                if (userRoleService.insert(userRole)) {
                    continue;
                }
                return new ResultUtil(500, "failure");
            }
        }
        return new ResultUtil(200, "success");
    }
}
