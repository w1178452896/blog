package com.taylorsfan.blog.controller.admin;

import com.taylorsfan.blog.model.Comment;
import com.taylorsfan.blog.service.BlogService;
import com.taylorsfan.blog.service.CommentService;
import com.taylorsfan.blog.service.UserService;
import com.taylorsfan.blog.service.relation.BlogCommentService;
import com.taylorsfan.blog.service.relation.UserCommentService;
import com.taylorsfan.blog.util.MapUtil;
import com.taylorsfan.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tianle
 */
@RestController
@RequestMapping("admin/comment")
public class DoCommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BlogService blogService;

    @Autowired
    public DoCommentController(CommentService commentService, UserCommentService userCommentService,
                               BlogCommentService blogCommentService, UserService userService,
                               BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
        this.userService = userService;
    }

    //   后台
    @RequestMapping("/all")
    public List<CommentVo> commentVoList(int pageNum, int pageSize) {
        Map<String, Integer> map = MapUtil.int2map(pageNum, pageSize);
        List<Comment> commentList = commentService.showAll(map);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            commentVo.setComment(comment);
            int blogId = blogService.showOneByCommentId(comment.getId()).getId();
            commentVo.setBlogId(blogId);
            commentVo.setCommentUser(userService.showUserByCommentId(comment.getId()));
            if (comment.getParentId() != 0) {
                commentVo.setBeCommentedUser(userService.showUserByCommentId(comment.getParentId()));
            } else {
                commentVo.setBeCommentedUser(userService.showUserByBlogId(blogId));
            }
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }
}
