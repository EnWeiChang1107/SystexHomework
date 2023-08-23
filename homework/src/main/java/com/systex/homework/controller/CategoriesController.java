package com.systex.homework.controller;

import com.systex.homework.entity.Categories;
import com.systex.homework.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriesController {
    private CategoriesService categoriesService;
    @Autowired
    public CategoriesController(CategoriesService categoriesService){
        this.categoriesService=categoriesService;
    }

    @GetMapping("/getAllCategories")
    public List<Categories> getAll(){
        return categoriesService.findAll();
    }

    @PostMapping("/addCategory")
    public Categories addCategory(@RequestBody Categories categories){
        categories.setId(0);
        Categories categories1=categoriesService.save(categories);
        return categories1;
    }

    @PutMapping("/updateCategory")
    public Categories updateCategory(@RequestBody Categories categories){
        Categories categories1=categoriesService.findById(categories.getId());
        categories.setCreatedAt(categories1.getCreatedAt());
        Categories categories2=categoriesService.save(categories);
        return categories2;
    }

    @DeleteMapping("/deleteCategory/{CategoryId}")
    public String deleteCategory(int CategoryId){
        Categories categories=categoriesService.findById(CategoryId);
        if(categories==null){
            throw new RuntimeException("Category id not found - " + CategoryId);
        }
        categoriesService.delete(CategoryId);
        return "Deleted category id: "+CategoryId;
    }
}
