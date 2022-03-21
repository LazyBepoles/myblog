package cn.liang.controller;

import cn.liang.pojo.Blog;
import cn.liang.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesShowController {

    @Autowired
    private BlogsService blogsService;

    @GetMapping("/archives")
    public String archives(Model model) {
        Map<String, List<Blog>> map = blogsService.archiveBlog();
        model.addAttribute("count",blogsService.countBlogs());
        model.addAttribute("blogMap",map);
        return "archives";
    }

}
