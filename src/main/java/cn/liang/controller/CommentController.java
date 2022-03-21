package cn.liang.controller;

import cn.liang.pojo.Comment;
import cn.liang.pojo.User;
import cn.liang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{id}")
    public String queryCommentsByBlogId(@PathVariable int id, Model model){
        List<Comment> commentList = commentService.queryByBlogId(id);
        model.addAttribute("comments",commentList);
        return "blogs :: commentList";
    }

    @PostMapping("/comments")
    public String addComment(Comment comment, HttpSession session){
        int blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if(null!=user){
            comment.setAvatar(user.getAvatar());
            comment.setIsadmin(true);
        }else {
            comment.setAvatar(avatar);
        }
        comment.setCreateTime(new Date());
        int result = commentService.addComment(comment);
        return "redirect:/comments/"+blogId;
    }
}
