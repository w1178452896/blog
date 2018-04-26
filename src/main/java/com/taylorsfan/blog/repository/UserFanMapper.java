package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.UserFan;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface UserFanMapper {
    int countFan(@Param("userId") int userId);

    int insert(@Param("userFan") UserFan userFan);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByFanId(@Param("fanId") int fanId);
}