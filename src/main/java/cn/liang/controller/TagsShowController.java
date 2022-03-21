package cn.liang.controller;

import cn.liang.pojo.Blog;
import cn.liang.pojo.Tag;
import cn.liang.service.BlogsService;
import cn.liang.service.TagsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagsShowController {

    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogsService blogsService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable int id, Model model, @RequestParam(defaultValue = "1") int pageNum) {
        String orderTagBy = "count desc";
        PageHelper.startPage(1,50,orderTagBy);
        List<Tag> tagList = tagsService.queryTagsTop();
        if(id == -1){
            id = tagList.get(0).getId();
        }
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList = blogsService.queryBlogsByTagId(id);
        PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("blogInfo",blogInfo);
        model.addAttribute("tagInfo",tagList);
        model.addAttribute("currentId",id);
        return "tags";
    }

    @PostMapping("/tags/next")
    public String tagsNext(@RequestParam int id, Model model, @RequestParam(defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList = blogsService.queryBlogsByTagId(id);
        PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("blogInfo",blogInfo);
        model.addAttribute("currentId",id);
        return "tags :: tagList";
    }
}
