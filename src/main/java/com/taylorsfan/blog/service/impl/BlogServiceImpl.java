package com.taylorsfan.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.repository.BlogMapper;
import com.taylorsfan.blog.service.BlogService;
import com.taylorsfan.blog.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }


    /**
     * 首页默认显示
     * 首页分类显示
     * 个人页面默认显示
     * 个人页面分类显示
     * 个人页面按状态显示  (正常显示，需要更改的)
     * 。
     */
    @Override
    public List<Blog> showAll(Map<String, Integer> map) {
        List<Blog> blogList;
        //分页
        PageHelper.startPage(map.get("pageNum"), map.get("pageSize"));
        if (map.get("page") == 1) {
            // 用户主页按分类查询
            if (map.containsKey("userId") && map.containsKey("sortId")) {
                blogList = blogMapper.selectAllNormalBySortIdAndUserId(map.get("userId"), map.get("sortId"));
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
            //用户个人主页显示
            else if (map.containsKey("userId") && !map.containsKey("sortId")) {
                blogList = blogMapper.selectAllNormalByUserId(map.get("userId"));
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
            //首页显示按分类
            else if (!map.containsKey("userId") && map.containsKey("sortId")) {
                blogList = blogMapper.selectAllNormalBySortId(map.get("sortId"));
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
            //首页显示
            else if (!map.containsKey("userId") && !map.containsKey("sortId")) {
                blogList = blogMapper.selectAllNormal();
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
        } else if (map.get("page") == 2) {
            // 后台按用户
            if (map.containsKey("userId") && !map.containsKey("status") && !map.containsKey("sortId")) {
                blogList = blogMapper.selectAllByUserId(map.get("userId"));
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
            // 后台按分类
            else if (!map.containsKey("userId") && !map.containsKey("status") && map.containsKey("sortId")) {
                blogList = blogMapper.selectAllBySortId(map.get("sortId"));
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
            //后台按状态查找
            else if (!map.containsKey("userId") && map.containsKey("status") && map.containsKey("sortId")) {
                blogList = blogMapper.selectAllByStatus(map.get("status"));
            }
            //后台所有
            else if (!map.containsKey("userId") && !map.containsKey("status") && !map.containsKey("sortId")) {
                blogList = blogMapper.selectAll();
                PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
                return pageInfo.getList();
            }
        }
        return null;
    }

    @Override
    public Blog showOne(int blogId) {
        return blogMapper.selectOneByPrimaryKey(blogId);
    }

    @Override
    public boolean update(Blog blog) {
        return blogMapper.updateByPrimaryKey(blog) != 0;
    }

    @Override
    public boolean delete(int blogId) {
        return blogMapper.deleteByPrimaryKey(blogId) != 0;
    }

    @Override
    public boolean insert(Blog blog) {
        return blogMapper.insert(blog) != 0;
    }

    @Override
    public Blog showOneByCommentId(int commentId) {
        return blogMapper.selectOneByCommentId(commentId);
    }

    @Override
    public boolean checkBlog(int blogId, boolean passed) {
        if (passed) {
            return blogMapper.updateBlogByStatus(blogId, 1) != 0;
        } else {
            return blogMapper.updateBlogByStatus(blogId, 3) != 0;
        }
    }

}
