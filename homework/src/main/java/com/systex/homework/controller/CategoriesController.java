package com.systex.homework.controller;

import com.systex.homework.entity.Categories;
import com.systex.homework.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoriesController {
    private CategoriesService categoriesService;
    @Autowired
    public CategoriesController(CategoriesService categoriesService){
        this.categoriesService=categoriesService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Categories>> getAll(){
        List<Categories>list=categoriesService.findAll();
         return ResponseEntity.ok().body(list);
    }

    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories categories){
        categories.setId(0);
        Categories categories1=categoriesService.save(categories);
        return ResponseEntity.ok().body(categories1);
    }

    @PutMapping("/update")
    public ResponseEntity<Categories> updateCategory(@RequestBody Categories categories){
        Categories categories1=categoriesService.findById(categories.getId());
        categories.setCreatedAt(categories1.getCreatedAt());
        Categories categories2=categoriesService.save(categories);
        return ResponseEntity.ok().body(categories2);
    }

    @DeleteMapping("/delete/{CategoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int CategoryId){
        Categories categories=categoriesService.findById(CategoryId);
        categoriesService.delete(CategoryId);
        return ResponseEntity.ok().body("Deleted category id: "+CategoryId) ;
    }
}
