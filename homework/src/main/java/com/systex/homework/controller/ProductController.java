package com.systex.homework.controller;

import com.systex.homework.entity.Product;
import com.systex.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/getProduct")
    public List<Product> getAll(){
        return productService.findAll();
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        product.setProductId(0);
        Product product1=productService.save(product);
        return product1;
    }
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product){
        System.out.println(product.getProductId());
        Product product1=productService.findById(product.getProductId());
        product.setCreatedAt(product1.getCreatedAt());
        Product product2=productService.save(product);
        return product2;
    }
    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId){
        Product product=productService.findById(productId);
        if(product==null){
            throw new RuntimeException("Did not find product id: "+productId);
        }
        productService.delete(productId);
        return "Deleted Product id: "+productId;
    }
}
