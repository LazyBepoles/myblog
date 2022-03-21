package cn.liang.controller;

import cn.liang.pojo.Blog;
import cn.liang.pojo.Tag;
import cn.liang.pojo.Type;
import cn.liang.service.*;
import cn.liang.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

  @Autowired
  private TagsService tagsService;
  @Autowired
  private TypesService typesService;
  @Autowired
  private BlogsService blogsService;
  @Autowired
  private UserService userService;
  @Autowired
  private BlogTagService blogTagService;

  @GetMapping(value = {"/","/index"})
  public String index(@RequestParam(defaultValue = "1") int pageNum, Model model) {
    String orderTypeBy = "count desc";
    PageHelper.startPage(1,6,orderTypeBy);
    List<Type> typeList = typesService.queryTypesTop();
    model.addAttribute("typeInfo",typeList);

    String orderTagBy = "count desc";
    PageHelper.startPage(1,10,orderTagBy);
    List<Tag> tagList = tagsService.queryTagsTop();
    model.addAttribute("tagInfo",tagList);

    String orderBy = "update_time desc";
    PageHelper.startPage(pageNum,10,orderBy);
    List<Blog> blogList = blogsService.queryAllPublishedBlogs();
    PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
    model.addAttribute("blogInfo",blogInfo);

    List<Blog> recommendList = new ArrayList<>();
    if(blogList.size()<=5){
      for (Blog blog : blogList) {
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        recommendList.add(b);
      }
    }else {
      for(int i=0;i<5;i++){
        Blog b = new Blog();
        BeanUtils.copyProperties(blogList.get(i),b);
        recommendList.add(b);
      }
    }
    model.addAttribute("recommendInfo",recommendList);
    return "index";
  }

  @PostMapping(value = {"/index/next"})
  public String indexNext(@RequestParam(defaultValue = "1") int pageNum, Model model) {
    String orderBy = "update_time desc";
    PageHelper.startPage(pageNum,10,orderBy);
    List<Blog> blogList = blogsService.queryAllPublishedBlogs();
    PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
    model.addAttribute("blogInfo",blogInfo);
    return "index :: indexList";
  }

  @PostMapping("/search")
  public String search(@RequestParam(defaultValue = "1") int pageNum, @RequestParam String query, Model model){
    PageHelper.startPage(pageNum,10);
    List<Blog> blogList = blogsService.searchIndexBlogs(query);
    PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
    model.addAttribute("blogInfo",blogInfo);
    model.addAttribute("query",query);
    return "search";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }

  @GetMapping("/blog/{id}")
  public String blog(@PathVariable int id,Model model) {
    Blog blog = blogsService.getBlogById(id);
    String content = blog.getContent();
    blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
    model.addAttribute("blog",blog);
    return "blogs";
  }

}
