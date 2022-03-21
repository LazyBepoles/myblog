package cn.liang.service;

import cn.liang.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogsService {

    List<Blog> queryAllBlogs();

    List<Blog> queryAllPublishedBlogs();

    List<Blog> searchBlogs(Blog blog);

    int addBlog(Blog blog,String tagsIds);

    Blog getBlogById(int id);

    int updateBlog(Blog blog,String tagsIds);

    int deleteBlog(@Param("id") int id);

    List<Blog> searchIndexBlogs(String query);

    List<Blog> queryBlogsByTypeId(int id);

    List<Blog> queryBlogsByTagId(int id);

    Map<String,List<Blog>> archiveBlog();

    int countBlogs();
}
