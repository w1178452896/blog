package com.taylorsfan.blog.service;

import com.taylorsfan.blog.model.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
public interface BlogService extends BaseService<Blog> {

    Blog showOneByCommentId(int commentId);

    boolean checkBlog(int blogId,boolean passed);
}
