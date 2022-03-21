package cn.liang.service;

import cn.liang.dao.BlogsMapper;
import cn.liang.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogsServiceImpl implements BlogsService{

    @Autowired
    private BlogsMapper blogsMapper;

    @Override
    public List<Blog> queryAllBlogs() {
        List<Blog> blogList = blogsMapper.queryAllBlogs();
        return blogList;
    }

    @Override
    public List<Blog> queryAllPublishedBlogs() {
        List<Blog> blogList = blogsMapper.queryAllPublishedBlogs();
        return blogList;
    }

    @Override
    public List<Blog> searchBlogs(Blog blog) {
        List<Blog> query = blogsMapper.searchBlogs(blog);
        return query;
    }

    @Transactional
    @Override
    public int addBlog(Blog blog,String tagsIds) {
        int result = blogsMapper.addBlog(blog);
        if(!tagsIds.isEmpty()){
            int addBlogTag = blogsMapper.addBlogTag(blog);
            return addBlogTag;
        }else {
            return result;
        }

    }

    @Override
    public Blog getBlogById(int id) {
        Blog blog = blogsMapper.getBlogById(id);
        return blog;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog,String tagsIds) {
        int result = blogsMapper.updateBlog(blog);
        if(!tagsIds.isEmpty()){
            int updateBlogTag = blogsMapper.addBlogTag(blog);
            return updateBlogTag;
        }else {
            return result;
        }

    }

    @Transactional
    @Override
    public int deleteBlog(int id) {
        int result = blogsMapper.deleteBlog(id);
        return result;
    }

    @Override
    public List<Blog> searchIndexBlogs(String query) {
        List<Blog> blogList = blogsMapper.searchIndexBlogs(query);
        return blogList;
    }

    @Override
    public List<Blog> queryBlogsByTypeId(int id) {
        List<Blog> blogList = blogsMapper.queryBlogsByTypeId(id);
        return blogList;
    }

    @Override
    public List<Blog> queryBlogsByTagId(int id) {
        List<Blog> blogList = blogsMapper.queryBlogsByTagId(id);
        return blogList;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogsMapper.queryGroupYear();
        Map<String,List<Blog>> map = new HashMap<>();
        for (String year : years){
            map.put(year,blogsMapper.queryBlogsByYear(year));
        }
        return map;
    }

    @Override
    public int countBlogs() {
        int countBlogs = blogsMapper.countBlogs();
        return countBlogs;
    }
}
