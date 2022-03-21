package cn.liang.controller.admin;

import cn.liang.pojo.Type;
import cn.liang.service.TypesService;
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
public class TypesController {

    @Autowired
    private TypesService typesService;

    @GetMapping("/types/add")
    public String toTypeInputPage(Model model) {
        model.addAttribute("type",new Type());
        return "admin/adminTypes-input";
    }

    @GetMapping("/types")
    public String queryAllTypes(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Type> typeList = typesService.queryAllTypes();
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/adminTypes";
    }

    @PostMapping("/types/search")
    public String searchTags(@RequestParam(defaultValue = "1") int pageNum,Model model,@RequestParam String name){
        PageHelper.startPage(pageNum,5);
        List<Type> typeList = typesService.queryTypesByName(name);
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/adminTypes :: typeList";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){
        int result = typesService.addType(type);
        if(result>0){
            attributes.addFlashAttribute("message","新增分类成功！");
        }else{
            attributes.addFlashAttribute("message","新增分类失败！");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}")
    public String getUpdateTypeId(@PathVariable int id,Model model){
        Type type = typesService.queryTypeById(id);
        model.addAttribute("type",type);
        return "admin/adminTypes-input";
    }

    @PutMapping("/types")
    public String updateType(Type type, RedirectAttributes attributes){
        int result = typesService.updateType(type);
        if(result>0){
            attributes.addFlashAttribute("message","更新分类成功！");
        }else{
            attributes.addFlashAttribute("message","更新分类失败！");
        }
        return "redirect:/admin/types";
    }

    @DeleteMapping("/types")
    public String deleteType(@RequestParam int id,@RequestParam(defaultValue = "1") int pageNum, RedirectAttributes attributes){
        int result = typesService.deleteType(id);
        if(result>0){
            attributes.addFlashAttribute("message","删除分类成功！");
        }else{
            attributes.addFlashAttribute("message","删除分类失败！");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/checkType")
    public ResponseEntity<Void> checkType(@RequestParam String name){
        Type type = typesService.queryByName(name);
        if(null!=type){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
