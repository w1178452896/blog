package com.taylorsfan.blog.controller;

import com.taylorsfan.blog.model.User;
import com.taylorsfan.blog.service.CommentService;
import com.taylorsfan.blog.service.UserService;
import com.taylorsfan.blog.service.vo.BlogVoService;
import com.taylorsfan.blog.service.vo.CommentVoService;
import com.taylorsfan.blog.service.vo.UserVoService;
import com.taylorsfan.blog.util.StringsUtil;
import com.taylorsfan.blog.util.VerifyCodeUtils;
import com.taylorsfan.blog.vo.BlogVo;
import com.taylorsfan.blog.vo.CommentVo;
import com.taylorsfan.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * @author taylorsfan
 */
@Controller
public class IndexController {
    private final BlogVoService blogVoService;
    private final UserVoService userVoService;
    private final UserService userService;
    private final CommentVoService commentVoService;

    @Autowired
    public IndexController(BlogVoService blogVoService, UserVoService userVoService, CommentVoService commentVoService, UserService userService) {
        this.blogVoService = blogVoService;
        this.userVoService = userVoService;
        this.commentVoService = commentVoService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<BlogVo> blogVoList = blogVoService.blogVoList(1, 5, 0, 0, 0, StringsUtil.FRONT);
        model.addAttribute("blogVoList", blogVoList);
        return "index";
    }

    @RequestMapping("/pull")
    public @ResponseBody List<BlogVo> pull(int pageNum) {
        List<BlogVo> blogVoList = blogVoService.blogVoList(pageNum, 5, 0, 0, 0, StringsUtil.FRONT);
        String temp = null;
        for (BlogVo blogVo : blogVoList) {
            if (blogVo.getBlog().getContent().length() > 101) {
                temp = blogVo.getBlog().getContent().substring(0, 100);
                blogVo.getBlog().setContent(temp);
            }
        }
        return blogVoList;
    }

    @RequestMapping("/user/{userId}")
    public String userIndex(Model model, HttpSession httpSession, @PathVariable int userId, String span) {
        List<UserVo> userVoList;
        List<BlogVo> blogVoList;
        List<CommentVo> commentVoList;
        //从session获取user信息，减少数据库查询
        User user = (User) httpSession.getAttribute("user");
        //点击到关注按钮
        if (span.equals(StringsUtil.FOCUS)) {
            userVoList = userVoService.userVoList(userId, 0, 1, 1, 5);
            model.addAttribute("userVoList", userVoList);
        }
        //点击到粉丝按钮
        else if (span.equals(StringsUtil.FAN)) {
            userVoList = userVoService.userVoList(userId, 1, 0, 1, 5);
            model.addAttribute("userVoList", userVoList);
        }
        //点击到文章按钮
        else if (span.equals(StringsUtil.ARTICLE)) {
            blogVoList = blogVoService.blogVoList(1, 5, 1, userId, 0, StringsUtil.FRONT);
            model.addAttribute("blogVoList", blogVoList);
        }
        //点击到最新评论按钮
        else if (span.equals(StringsUtil.COMMENT)) {
            commentVoList = commentVoService.commentVoListUser(1, 20, userId);
            model.addAttribute("commentVoList", commentVoList);
        }
        UserVo userVo = userVoService.userVo(userId, user != null);
        userVo.setUser(user);
        httpSession.setAttribute("userVo", user);
        return "user/index";
    }

    @RequestMapping("/settings/basic")
    public String showUser(Model model, int userId) {
        model.addAttribute("user", userService.showOne(userId));
        return "user/user";
    }
    //    审核评论

    @RequestMapping("/veryCode")
    public void veryCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("veryCode");
        session.setAttribute("veryCode", verifyCode.toLowerCase());
        //生成图片
        int w = 110, h = 40;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    @RequestMapping("/uploadPic")
    public void uploadPic(HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile multipartFile) {
        try {
            String pictureName = multipartFile.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + pictureName.substring(pictureName.lastIndexOf("."));
            String uploadDir = "F:/Pictures/";
            File file = new File(uploadDir + newFileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            multipartFile.transferTo(file);
            String success = "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/upload/" + newFileName + "\"}";
            response.getWriter().write(success);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("{\"success\":0, \"message\":\"上传失败\"}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RequestMapping("/blog/{blogId}")
    public String blogVo(Model model, @PathVariable int blogId) {
        model.addAttribute("blogVo", blogVoService.blogVo(blogId));
        return "/user/blogVo";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }

}
