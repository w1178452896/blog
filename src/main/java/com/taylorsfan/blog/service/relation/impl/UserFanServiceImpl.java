package com.taylorsfan.blog.service.relation.impl;

import com.taylorsfan.blog.model.relation.UserFan;
import com.taylorsfan.blog.repository.UserFanMapper;
import com.taylorsfan.blog.service.relation.UserFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianle
 */
@Service
public class UserFanServiceImpl implements UserFanService {
    private final UserFanMapper userFanMapper;

    @Autowired
    public UserFanServiceImpl(UserFanMapper userFanMapper) {
        this.userFanMapper = userFanMapper;
    }

    @Override
    public int count(int id) {
        return userFanMapper.countFan(id);
    }

    @Override
    public boolean insert(UserFan userFan) {
        return userFanMapper.insert(userFan) != 0;
    }

    @Override
    public boolean deleteByOneId(int id) {
        return userFanMapper.deleteByUserId(id) != 0;
    }

    @Override
    public boolean deleteByMoreId(int id) {
        return userFanMapper.deleteByFanId(id) != 0;
    }
}
