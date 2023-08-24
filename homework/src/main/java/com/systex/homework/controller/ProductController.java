package com.systex.homework.controller;

import com.systex.homework.entity.Product;
import com.systex.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAll(){
        return productService.findAll();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        product.setProduct_Id(0);
        Product product1=productService.save(product);
        return product1;
    }
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        Product product1=productService.findById(product.getProduct_Id());
        product.setCreatedAt(product1.getCreatedAt());
        Product product2=productService.save(product);
        return product2;
    }
    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable int productId){
        Product product=productService.findById(productId);
        if(product==null){
            throw new RuntimeException("Did not find product id: "+productId);
        }
        productService.delete(productId);
        return "Deleted Product id: "+productId;
    }
}
