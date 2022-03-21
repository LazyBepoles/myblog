package cn.liang.controller;

import cn.liang.pojo.Blog;
import cn.liang.pojo.Type;
import cn.liang.service.BlogsService;
import cn.liang.service.TypesService;
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
public class TypesShowController {

    @Autowired
    private TypesService typesService;
    @Autowired
    private BlogsService blogsService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable int id, Model model, @RequestParam(defaultValue = "1") int pageNum) {
        String orderTypeBy = "count desc";
        PageHelper.startPage(1,50,orderTypeBy);
        List<Type> typeList = typesService.queryTypesTop();
        if(id == -1){
            id = typeList.get(0).getId();
        }
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList = blogsService.queryBlogsByTypeId(id);
//        for (Blog blog : blogList){
//            blog.setType(typesService.queryTypeById(blog.getTypeId()));
//            blog.setUser(userService.getUser(blog.getUserId()));
//        }
        PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("blogInfo",blogInfo);
        model.addAttribute("typeInfo",typeList);
        model.addAttribute("currentId",id);
        return "types";
    }

    @PostMapping("/types/next")
    public String typesNext(@RequestParam int id, Model model, @RequestParam(defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList = blogsService.queryBlogsByTypeId(id);
//        for (Blog blog : blogList){
//            blog.setType(typesService.queryTypeById(blog.getTypeId()));
//            blog.setUser(userService.getUser(blog.getUserId()));
//        }
        PageInfo<Blog> blogInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("blogInfo",blogInfo);
//        model.addAttribute("currentId",id);
        return "types :: typeList";
    }
}
