package com.systex.homework.controller;

import com.systex.homework.entity.Categories;
import com.systex.homework.response.ErrorRes;
import com.systex.homework.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity getAll(){
        try {
            List<Categories> list = categoriesService.findAll();
            return ResponseEntity.ok().body(list);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody Categories categories){
        try {
            categories.setId(0);
            Categories categories1 = categoriesService.save(categories);
            return new ResponseEntity<>(categories1, HttpStatus.CREATED);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateCategory(@RequestBody Categories categories){
        try {
            Categories categories1 = categoriesService.findById(categories.getId());
            categories.setCreatedAt(categories1.getCreatedAt());
            Categories categories2 = categoriesService.save(categories);
            return ResponseEntity.ok().body(categories2);
        }catch(NullPointerException e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND,"Employee NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

    @DeleteMapping("/delete/{CategoryId}")
    public ResponseEntity deleteCategory(@PathVariable int CategoryId){
        try {
            Categories categories = categoriesService.findById(CategoryId);
            categoriesService.delete(CategoryId);
            return ResponseEntity.ok().body("Deleted category id: " + CategoryId);
        }catch (NullPointerException e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND,"Employee NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
}
