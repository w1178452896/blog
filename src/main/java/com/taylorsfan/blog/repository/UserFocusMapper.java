package com.taylorsfan.blog.repository;

import com.taylorsfan.blog.model.relation.UserFocus;
import org.apache.ibatis.annotations.Param;

/**
 * @author taylorsfan
 */
public interface UserFocusMapper {

    int countFocus(@Param("userId") int userId);

    int insert(@Param("userFocus") UserFocus userFocus);

    int deleteByUserId(@Param("userId") int userId);

    int deleteByFocusId(@Param("focusId") int focusId);
}