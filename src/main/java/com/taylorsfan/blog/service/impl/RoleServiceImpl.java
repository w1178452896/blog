package com.taylorsfan.blog.service.impl;

import com.taylorsfan.blog.model.Role;
import com.taylorsfan.blog.repository.RoleMapper;
import com.taylorsfan.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author momo
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> showAll(Map<String, Integer> map) {
        if (map.containsKey("userId")) {
            return roleMapper.selectAllByUserId(map.get("userId"));
        } else if (!map.containsKey("userId")) {
            return roleMapper.selectAll();
        }
        return null;
    }

    @Override
    public boolean update(Role role) {
        return roleMapper.updateByPrimaryKey(role) != 0;
    }

    @Override
    public boolean delete(int id) {
        return roleMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Role showOne(int id) {
        return null;
    }

    @Override
    public boolean insert(Role role) {
        return roleMapper.insert(role) != 0;
    }

}
