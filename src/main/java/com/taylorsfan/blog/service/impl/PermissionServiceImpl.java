package com.taylorsfan.blog.service.impl;

import com.taylorsfan.blog.model.Permission;
import com.taylorsfan.blog.repository.PermissionMapper;
import com.taylorsfan.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> showAll(Map<String, Integer> map) {
        //根据用户查询
        if (map.containsKey("userId") && !map.containsKey("roleId")) {
            return permissionMapper.selectAllByUserId(map.get("userId"));
            //根据角色查询
        } else if (!map.containsKey("userId") && map.containsKey("roleId")) {
            return permissionMapper.selectAllByRoleId(map.get("roleId"));
            //查看所有
        } else if (!map.containsKey("userId") && !map.containsKey("roleId")) {
            return permissionMapper.selectAll();
        }
        return null;
    }

    @Override
    public boolean update(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission) != 0;
    }

    @Override
    public boolean delete(int id) {
        return permissionMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public boolean insert(Permission permission) {
        return permissionMapper.insert(permission) != 0;
    }

    @Override
    public Permission showOne(int id) {
        return permissionMapper.selectOneByPrimaryKey(id);
    }

}
