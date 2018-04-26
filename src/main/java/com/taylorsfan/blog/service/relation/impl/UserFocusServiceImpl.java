package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.UserFocus;
import com.taylorsfan.blog.repository.UserFocusMapper;
import com.taylorsfan.blog.service.relation.UserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class UserFocusServiceImpl implements UserFocusService {
    private final UserFocusMapper userFocusMapper;

    @Autowired
    public UserFocusServiceImpl(UserFocusMapper userFocusMapper) {
        this.userFocusMapper = userFocusMapper;
    }

    @Override
    public int count(int id) {
        return userFocusMapper.countFocus(id);
    }

    @Override
    public boolean insert(UserFocus userFocus) {
        return userFocusMapper.insert(userFocus) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return userFocusMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return userFocusMapper.deleteByFocusId(id) != 0;
    }
}
