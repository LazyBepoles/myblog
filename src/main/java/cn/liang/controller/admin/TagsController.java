package cn.liang.controller.admin;

import cn.liang.pojo.Tag;
import cn.liang.service.TagsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags/add")
    public String toTagInputPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/adminTags-input";
    }

    @GetMapping("/tags")
    public String queryAllTags(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Tag> tagList = tagsService.queryAllTags();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tagList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/adminTags";
    }

    @PostMapping("/tags/search")
    public String searchTags(@RequestParam(defaultValue = "1") int pageNum,Model model,@RequestParam String name){
        PageHelper.startPage(pageNum,5);
        List<Tag> tagList = tagsService.queryTagsByName(name);
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tagList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/adminTags :: tagList";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes){
        int result = tagsService.addTag(tag);
        if(result>0){
            attributes.addFlashAttribute("message","新增标签成功！");
        }else{
            attributes.addFlashAttribute("message","新增标签失败！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}")
    public String getUpdateTagId(@PathVariable int id,Model model){
        Tag tag = tagsService.queryTagById(id);
        model.addAttribute("tag",tag);
        return "admin/adminTags-input";
    }

    @PutMapping("/tags")
    public String updateTag(Tag tag, RedirectAttributes attributes){
        int result = tagsService.updateTag(tag);
        if(result>0){
            attributes.addFlashAttribute("message","更新标签成功！");
        }else{
            attributes.addFlashAttribute("message","更新标签失败！");
        }
        return "redirect:/admin/tags";
    }

    @DeleteMapping("/tags")
    public String deleteTag(@RequestParam int id,@RequestParam int pageNum, RedirectAttributes attributes){
        int result = tagsService.deleteTag(id);
        if(result>0){
            attributes.addFlashAttribute("message","删除标签成功！");
        }else{
            attributes.addFlashAttribute("message","删除标签失败！");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/checkTag")
    public ResponseEntity<Void> checkTag(@RequestParam String name){
        Tag tag = tagsService.queryTagByName(name);
        if(null!=tag){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
