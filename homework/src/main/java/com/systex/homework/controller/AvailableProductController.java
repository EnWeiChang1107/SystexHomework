package com.systex.homework.controller;

import com.systex.homework.entity.AvailableProduct;
import com.systex.homework.entity.Product;
import com.systex.homework.service.AvailableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AvailableProduct")
public class AvailableProductController {
    private AvailableProductService availableProductService;
    @Autowired
    public AvailableProductController(AvailableProductService availableProductService){
        this.availableProductService=availableProductService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AvailableProduct>> getAll(){
        List<AvailableProduct> list=availableProductService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<AvailableProduct> add(@PathVariable int id){
        AvailableProduct availableProduct=availableProductService.addProduct(id);
        return ResponseEntity.ok().body(availableProduct);
    }

    @PutMapping("/update")
    public ResponseEntity<AvailableProduct> update(@RequestBody AvailableProduct availableProduct){
        AvailableProduct availableProduct1=availableProductService.findById(availableProduct.getAvailableProductId());
        availableProduct.setCreatedAt(availableProduct1.getCreatedAt());
        AvailableProduct availableProduct2=availableProductService.updateProduct(availableProduct);
        return ResponseEntity.ok().body(availableProduct2);
    }
    @DeleteMapping("/delete/{AvailableProductId}")
    public ResponseEntity<String> delete(@PathVariable int AvailableProductId){
        availableProductService.deleteAvailableProduct(AvailableProductId);
        return ResponseEntity.ok().body("Deleted AvailableProduct id - "+AvailableProductId) ;
    }
}
