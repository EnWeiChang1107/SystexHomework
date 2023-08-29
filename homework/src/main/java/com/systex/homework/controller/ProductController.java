package com.systex.homework.controller;

import com.systex.homework.entity.Product;
import com.systex.homework.response.ErrorRes;
import com.systex.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try {
            List<Product> list = productService.findAll();
            return ResponseEntity.ok().body(list);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        try {
            product.setProduct_id(0);
            Product product1 = productService.save(product);

            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }catch (NullPointerException e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND,"Product NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product product){
        try {
            Product product1 = productService.findById(product.getProduct_id());
            product.setCreatedAt(product1.getCreatedAt());
            Product product2 = productService.save(product);
            return ResponseEntity.ok().body(product2);
        }catch (NullPointerException e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND,"Product NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        try {
            Product product = productService.findById(productId);
            productService.delete(productId);
            return ResponseEntity.ok().body("Deleted Product id: " + productId);

        }catch (NullPointerException e){
            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Product NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
}
