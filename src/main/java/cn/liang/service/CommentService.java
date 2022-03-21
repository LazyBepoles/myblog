package cn.liang.service;

import cn.liang.pojo.Comment;

import java.util.List;

public interface CommentService {

    int addComment(Comment comment);

    List<Comment> queryByBlogId(int id);

}
