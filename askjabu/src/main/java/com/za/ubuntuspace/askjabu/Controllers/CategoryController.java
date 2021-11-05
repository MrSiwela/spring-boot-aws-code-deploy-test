package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.Category;
import com.za.ubuntuspace.askjabu.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/categories")
    public String CategoriesPage(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList",categoryList);
        return "categories";
    }

    @GetMapping("/categories/new")
    public String addCategoriesPage(Model model){
        Category newCategory = new Category();
        model.addAttribute("newCategory",newCategory);
        return "addCategory";
    }

    @PostMapping("/categories/save")
    public String addCategory(Category newCategory,Model model){
        categoryService.addCategory(newCategory);
        return "redirect:/categories";
    }

    @GetMapping("/categories/remove/{id}")
    public String removeCategory(@PathVariable("id") int id, Model model){
        Category removeCategory =categoryService.removeCategory(id);
        model.addAttribute("deletedCategory",removeCategory);
        return "redirect:/categories";
    }
}
