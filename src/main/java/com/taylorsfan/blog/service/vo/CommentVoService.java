package com.taylorsfan.blog.service.vo;

import com.taylorsfan.blog.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianle
 */
@Service
public interface CommentVoService {

    List<CommentVo> commentVoListBlog(int pageNum, int pageSize, int blogId);

    List<CommentVo> commentVoListUser(int pageNum, int pageSize, int userId);
}
