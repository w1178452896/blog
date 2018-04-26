package com.taylorsfan.blog.controller.admin;

import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.service.PermissionService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author tianle
 */
@RestController
@RequestMapping("/admin/permission")
public class DoPermissionController {
    private final PermissionService permissionService;

    @Autowired
    public DoPermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @RequestMapping("/all")
    public List<Permission> permissionList() {
        return permissionService.showAll(new HashMap<>());
    }

    @RequestMapping("/insert")
    public ResultUtil insert(Permission permission) {
        permission.setId(IdUtil.createId());
        if (permissionService.insert(permission)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(400, "failure");
    }

    @RequestMapping("/{permissionId}/update")
    public ResultUtil update(Permission permission, @PathVariable int permissionId) {
        if (permissionService.update(permission)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(400, "failure");
    }

    @RequestMapping("/{permissionId}/delete")
    public ResultUtil delete(@PathVariable int permissionId) {
        if (permissionService.delete(permissionId)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(400, "failure");
    }
}
