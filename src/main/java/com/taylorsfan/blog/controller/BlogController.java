package com.taylorsfan.blog.controller;


import com.taylorsfan.blog.MQService.MQService;
import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.model.Comment;
import com.taylorsfan.blog.model.Sort;
import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.model.relation.BlogUser;
import com.taylorsfan.blog.model.relation.SortBlog;
import com.taylorsfan.blog.model.relation.UserBlog;
import com.taylorsfan.blog.service.BlogService;
import com.taylorsfan.blog.service.CommentService;
import com.taylorsfan.blog.service.SortService;
import com.taylorsfan.blog.service.relation.*;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import com.taylorsfan.blog.util.SolrUtil;
import com.taylorsfan.blog.util.StringsUtil;
import com.taylorsfan.blog.vo.BlogVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author taylorsfan
 * 个人主页的blogList，以及blog
 */
@RequestMapping("/blog")
@RestController
public class BlogController {
    private final BlogService blogService;
    private final SortService sortService;
    private final BlogUserService blogUserService;
    private final UserBlogService userBlogService;
    private final BlogCommentService blogCommentService;
    private final CommentService commentService;
    private final SortBlogService sortBlogService;
    private final CommentUserService commentUserService;
    private final UserCommentService userCommentService;
    
    @Autowired
	SolrUtil solr;
	@Autowired
	MQService mqService;

    @Autowired
    public BlogController(BlogService blogService, BlogUserService blogUserService,
                          BlogCommentService blogCommentService, CommentService commentService,
                          SortBlogService sortBlogService, UserBlogService userBlogService,
                          CommentUserService commentUserService, UserCommentService userCommentService, SortService sortService) {
        this.blogService = blogService;
        this.blogUserService = blogUserService;
        this.blogCommentService = blogCommentService;
        this.commentService = commentService;
        this.sortBlogService = sortBlogService;
        this.userBlogService = userBlogService;
        this.commentUserService = commentUserService;
        this.userCommentService = userCommentService;
        this.sortService = sortService;
    }
    
    @RequestMapping("/search")
    @ResponseBody
    public List<BlogVo> searchKeyWords(String keyWord,int pageNo){
    	return solr.querySolr(keyWord, null, (pageNo-1)*5, 5);
    }
    
    
    /**
     * 新增文章
     */
    @RequestMapping("/insert")
    public ResultUtil insert(BlogVo blogVo, HttpSession httpSession) {
        int id = IdUtil.createId();
        Blog blog = blogVo.getBlog();
        blog.setId(id);
        blog.setStatus(1);
        blogService.insert(blogVo.getBlog());
        User user = (User) httpSession.getAttribute("user");
        SortBlog sortBlog = new SortBlog();
        UserBlog userBlog = new UserBlog();
        sortBlog.setSortId(blogVo.getSort().getId());
        sortBlog.setBlogId(id);
        userBlog.setBlogId(id);
        userBlog.setUserId(user.getId());
        if (userBlogService.insert(userBlog) && sortBlogService.insert(sortBlog)) {
        	mqService.sendQueueMsg("zhongbei.yiban.queue", MQService.Add, blogVo);
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");

    }

    @RequestMapping("/sort/all")
    public List<Sort> SortAll() {
        return sortService.showAll(new HashMap<>());
    }

    /**
     * 删除
     */
    @RequestMapping("/{blogId}/delete")
    public ResultUtil delete(@PathVariable int blogId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("blogId", blogId);
        if (blogService.delete(blogId) && blogUserService.deleteByOneId(blogId)
                && userBlogService.deleteByMoreId(blogId) && blogCommentService.deleteByOneId(blogId)
                && commentService.deleteByBlogId(blogId)) {
            for (Comment comment : commentService.showAll(map)) {
                if (commentUserService.deleteByOneId(comment.getId()) &&
                        userCommentService.deleteByMoreId(comment.getId())) {
                    continue;
                }
                return new ResultUtil(500, "failure");
            }
        }
        mqService.sendQueueMsg("zhongbei.yiban.queue", MQService.DELETE, blogId+"");
        return new ResultUtil(200, "success");
    }

    /**
     * 更新
     */
    @RequestMapping("/{blogId}/update")
    public ResultUtil update(BlogVo blogVo, @PathVariable int blogId) {
        if (blogService.update(blogVo.getBlog())) {
            sortBlogService.deleteByMoreId(blogVo.getBlog().getId());
            SortBlog sortBlog = new SortBlog();
            sortBlog.setBlogId(blogVo.getSort().getId());
            sortBlog.setSortId(blogId);
            if (sortBlogService.insert(sortBlog)) {
            	mqService.sendQueueMsg("zhongbei.yiban.queue", MQService.Add, blogVo);
                return new ResultUtil(200, "success");
            }
        }
        return new ResultUtil(500, "failure");
    }

    @RequestMapping("/{blogId}/like")
    public ResultUtil like(@PathVariable int blogId, int userId, String operation) {
        //点赞
        if (operation.equals(StringsUtil.LIKE)) {
            BlogUser blogUser = new BlogUser();
            blogUser.setBlogId(blogId);
            blogUser.setUserId(userId);
            if (blogUserService.insert(blogUser)) {
                return new ResultUtil(500, "failure");
            }
            return new ResultUtil(200, "success");
        }
        //取消点赞
        else if (operation.equals(StringsUtil.UNLIKE)) {
            if (blogUserService.deleteByMoreId(userId)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        return new ResultUtil(500, "failure");

    }

    /**
     * 检查
     */
    @RequestMapping("/check/blog/{blogId}")
    public ResultUtil check(@PathVariable int blogId, String ifPassedOrNot) {
        //通过
        if (ifPassedOrNot.equals(StringsUtil.PASSED)) {
            if (blogService.checkBlog(blogId, true)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        //未通过
        else if (ifPassedOrNot.equals(StringsUtil.UN_PASSED)) {
            if (blogService.checkBlog(blogId, false)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        return new ResultUtil(500, "failure");
    }
/*
    @ResponseBody
    @RequestMapping("/1")
    public BlogVo testBlogVo() {
        BlogVo blogVo = new BlogVo();
        blogVo.setBlog(blogService.showOne(1));
        blogVo.setUser(userService.showUserByBlogId(1));
        blogVo.setSort(sortService.showSortByBlogId(1));
        blogVo.setUserCount(blogUserService.count(1));
        blogVo.setCommentCount(blogCommentService.count(1));
        return blogVo;
    }*/

    /*
    @ResponseBody
    @RequestMapping("/testAll")
    public List<BlogVo> testBlogVoList() {
        Map<String, Integer> map = MapUtil.int2map(1, 2);
        List<BlogVo> blogVoList = new ArrayList<>();
        List<Blog> blogList = blogService.showAll(map);
        for (Blog blog : blogList) {
            BlogVo blogVo = new BlogVo();
            int blogId = blog.getId();
            blogVo.setBlog(blogService.showOne(blogId));
            blogVo.setUser(userService.showUserByBlogId(blogId));
            blogVo.setSort(sortService.showSortByBlogId(blogId));
            blogVo.setCommentCount(blogCommentService.count(blogId));
            blogVo.setUserCount(blogUserService.count(blogId));
            blogVoList.add(blogVo);
        }
        return blogVoList;
    }*/

}
