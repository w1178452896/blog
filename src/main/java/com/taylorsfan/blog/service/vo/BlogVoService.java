package com.taylorsfan.blog.service.vo;

import com.taylorsfan.blog.vo.BlogVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianle
 */
@Service
public interface BlogVoService {

    BlogVo blogVo(int blogId);

    List<BlogVo> blogVoList(int pageNum, int pageSize, int status, int userId, int sortId, String page);
}
