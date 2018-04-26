package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.UserRole;
import com.taylorsfan.blog.repository.UserRoleMapper;
import com.taylorsfan.blog.service.relation.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public int count(int id) {
        return userRoleMapper.countRole(id);
    }

    @Override
    public boolean insert(UserRole userRole) {
        return userRoleMapper.insert(userRole) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return userRoleMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return userRoleMapper.deleteByRoleId(id) != 0;
    }
}
