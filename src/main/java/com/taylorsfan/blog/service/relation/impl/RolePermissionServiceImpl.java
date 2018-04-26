package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.RolePermission;
import com.taylorsfan.blog.repository.RolePermissionMapper;
import com.taylorsfan.blog.service.relation.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionMapper rolePermissionMapper;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public int count(int id) {
        return rolePermissionMapper.countPermission(id);
    }

    @Override
    public boolean insert(RolePermission rolePermission) {
        return rolePermissionMapper.insert(rolePermission) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return rolePermissionMapper.deleteByRoleId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return rolePermissionMapper.deleteByPermissionId(id) != 0;
    }
}
