package com.taylorsfan.blog.controller.admin;

import com.taylorsfan.blog.model.Role;
import com.taylorsfan.blog.model.relation.RolePermission;
import com.taylorsfan.blog.service.PermissionService;
import com.taylorsfan.blog.service.RoleService;
import com.taylorsfan.blog.service.relation.RolePermissionService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import com.taylorsfan.blog.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author tianle
 */
@RestController
@RequestMapping("/admin/role")
public class DoRoleController {
    private final RolePermissionService rolePermissionService;
    private final RoleService roleService;
    private final PermissionService permissionService;

    @Autowired
    public DoRoleController(RolePermissionService rolePermissionService, RoleService roleService, PermissionService permissionService) {
        this.rolePermissionService = rolePermissionService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    /**
     * 增加角色，分配权限
     */
    @RequestMapping("/insert")
    public ResultUtil insert(Role role, int permissionId) {
        int id = IdUtil.createId();
        role.setId(id);
        if (roleService.insert(role)) {
            if (rolePermissionService.insert(new RolePermission(id,id,permissionId))) {
                return new ResultUtil(200, "success");
            }
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 更改角色名
     */
    @RequestMapping("/{roleId}/update")
    public ResultUtil update(Role role, @PathVariable int roleId) {
        if (roleService.update(role)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /**
     * 展示一个
     */
    @RequestMapping("/{roleId}")
    public RoleVo roleVo(@PathVariable int roleId) {
        Role role = roleService.showOne(roleId);
        RoleVo roleVo = new RoleVo();
        roleVo.setRole(role);
        Map<String, Integer> map = new HashMap<>();
        map.put("roleId", role.getId());
        roleVo.setPermissionList(permissionService.showAll(map));
        return roleVo;
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<RoleVo> testRoleVoList() {
        List<Role> roleList = roleService.showAll(new HashMap<>());
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleVo roleVo = new RoleVo();
            roleVo.setRole(role);
            Map<String, Integer> map = new HashMap<>();
            map.put("roleId", role.getId());
            roleVo.setPermissionList(permissionService.showAll(map));
            roleVoList.add(roleVo);
        }
        return roleVoList;
    }
}
