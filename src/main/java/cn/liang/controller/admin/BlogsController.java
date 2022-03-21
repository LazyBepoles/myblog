package cn.liang.controller.admin;

import cn.liang.pojo.Blog;
import cn.liang.pojo.Tag;
import cn.liang.pojo.Type;
import cn.liang.pojo.User;
import cn.liang.service.BlogTagService;
import cn.liang.service.BlogsService;
import cn.liang.service.TagsService;
import cn.liang.service.TypesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogsController {

    @Autowired
    private BlogsService blogsService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/blogs/add")
    public String toBlogInputPage(Model model){

        model.addAttribute("blogInfo",new Blog());
        model.addAttribute("tagsInfo",tagsService.queryAllTags());
        model.addAttribute("typesInfo",typesService.queryAllTypes());
        return "admin/adminBlogs-input";
    }

    @GetMapping("/blogs")
    public String queryAllBlogs(@RequestParam(defaultValue = "1") int pageNum, Model model){

        String orderBy = "update_time " + "desc";
        PageHelper.startPage(pageNum,1,orderBy);
        List<Blog> blogList = blogsService.queryAllBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("pageInfo",pageInfo);

        List<Type> typeList = typesService.queryAllTypes();
        model.addAttribute("typeInfo",typeList);
        return "admin/adminBlogs";
    }

    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(defaultValue = "1") int pageNum,Blog blog,Model model){
        String orderBy = "update_time " + "desc";
        PageHelper.startPage(pageNum,1,orderBy);
        List<Blog> blogList = blogsService.searchBlogs(blog);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        model.addAttribute("pageInfo",pageInfo);
//        List<Type> typeList = typesService.queryAll();
//        model.addAttribute("typeInfo",typeList);
        return "admin/adminBlogs :: blogList";
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session,@RequestParam String tagsIds){

        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        if(!tagsIds.isEmpty()){
            List<Tag> tagList = tagsService.queryTags(tagsIds);
            blog.setTags(tagList);
        }
        int result = blogsService.addBlog(blog,tagsIds);
        if(result>0){
            attributes.addFlashAttribute("message","新增博客成功！");
        }else{
            attributes.addFlashAttribute("message","新增博客失败！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}")
    public String getUpdateBlogId(@PathVariable int id, Model model){
        model.addAttribute("tagsInfo",tagsService.queryAllTags());
        model.addAttribute("typesInfo",typesService.queryAllTypes());
        Blog blog = blogsService.getBlogById(id);
//        List<Tag> tags = blogTagService.getTagsByBlogId(id);
//        blog.setTags(tags);
        blog.init();
        model.addAttribute("blogInfo",blog);
        return "admin/adminBlogs-input";
    }

    @PutMapping("/blogs")
    public String updateBlog(Blog blog, RedirectAttributes attributes, HttpSession session,@RequestParam String tagsIds){
        blog.setUpdateTime(new Date());
        int result = blogTagService.deleteTags(blog.getId());
        if(!tagsIds.isEmpty()){
            List<Tag> tagList = tagsService.queryTags(tagsIds);
            blog.setTags(tagList);
        }
        int updateBlog = blogsService.updateBlog(blog,tagsIds);
        if(updateBlog>0){
            attributes.addFlashAttribute("message","修改博客成功！");
        }else{
            attributes.addFlashAttribute("message","修改博客失败！");
        }
        return "redirect:/admin/blogs";
    }

    @DeleteMapping("/blogs")
    public String deleteBlog(@RequestParam int id,@RequestParam(defaultValue = "1") int pageNum,
                             RedirectAttributes attributes){
        int result = blogsService.deleteBlog(id);
        if(result>0){
            attributes.addFlashAttribute("message","删除博客成功！");
        }else{
            attributes.addFlashAttribute("message","删除博客失败！");
        }
        return "redirect:/admin/blogs";
    }
}
