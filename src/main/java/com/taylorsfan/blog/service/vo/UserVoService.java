package com.taylorsfan.blog.service.vo;

import com.taylorsfan.blog.vo.UserVo;

import java.util.List;

/**
 * @author tianle
 */
public interface UserVoService {

    UserVo userVo(int userId,boolean userExists);

    List<UserVo> userVoList(int userId, int fan, int focus, int pageNum, int pageSize);
}
