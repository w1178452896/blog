package com.taylorsfan.blog.controller;

import com.taylorsfan.blog.model.relation.BlogComment;
import com.taylorsfan.blog.model.relation.CommentUser;
import com.taylorsfan.blog.model.relation.UserComment;
import com.taylorsfan.blog.service.CommentService;
import com.taylorsfan.blog.service.relation.BlogCommentService;
import com.taylorsfan.blog.service.relation.CommentUserService;
import com.taylorsfan.blog.service.relation.UserCommentService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import com.taylorsfan.blog.util.StringsUtil;
import com.taylorsfan.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author taylorsfan
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserCommentService userCommentService;
    private final BlogCommentService blogCommentService;
    private final CommentUserService commentUserService;

    @Autowired
    public CommentController(CommentService commentService, UserCommentService userCommentService,
                             BlogCommentService blogCommentService,
                             CommentUserService commentUserService) {
        this.commentService = commentService;
        this.blogCommentService = blogCommentService;
        this.userCommentService = userCommentService;
        this.commentUserService = commentUserService;
    }

    @RequestMapping("/insert")
    public ResultUtil insert(CommentVo commentVo) {
        int id = IdUtil.createId();
        if (commentService.insert(commentVo.getComment())) {
            UserComment userComment = new UserComment(id,commentVo.getCommentUser().getId(),id);
            BlogComment blogComment = new BlogComment(id,commentVo.getBlogId(),id);
            if (userCommentService.insert(userComment) && blogCommentService.insert(blogComment)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        return new ResultUtil(500, "failure");
    }

    @RequestMapping("/{commentId}/delete")
    public ResultUtil deleteByCommentId(@PathVariable int commentId) {
        if (commentService.deleteByCommentId(commentId)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    //点赞
    @RequestMapping("/{commentId}/like")
    public ResultUtil like(@PathVariable int commentId, int userId, String operation) {

        //点赞
        if (operation.equals(StringsUtil.LIKE)) {
            CommentUser commentUser = new CommentUser();
            commentUser.setCommentId(commentId);
            commentUser.setUserId(userId);
            if (commentUserService.insert(commentUser)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        //取消点赞
        else if (operation.equals(StringsUtil.UNLIKE)) {
            if (commentUserService.deleteByMoreId(userId)) {
                return new ResultUtil(200, "success");
            }
            return new ResultUtil(500, "failure");
        }
        return new ResultUtil(500, "failure");

    }

    /**
     * 检查
     */
    @RequestMapping("/check/comment/{commentId}")
    public ResultUtil check(@PathVariable int commentId) {
        if (commentService.updateByChecker(commentId)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(500, "failure");
    }

    /*
    @ResponseBody
    @RequestMapping("/list/testAll")
    public List<CommentVo> adminAll() {
        Map<String, Integer> map = MapUtil.int2map(1, 2);
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

    }*/

}
