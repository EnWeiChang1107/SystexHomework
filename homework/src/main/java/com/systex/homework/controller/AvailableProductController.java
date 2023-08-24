package com.systex.homework.controller;

import com.systex.homework.entity.AvailableProduct;
import com.systex.homework.entity.Product;
import com.systex.homework.service.AvailableProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AvailableProduct> getAll(){
        return availableProductService.findAll();
    }

    @PostMapping("/add/{id}")
    public AvailableProduct add(@PathVariable int id){
        return availableProductService.addProduct(id);
    }

    @PutMapping("/update")
    public AvailableProduct update(@RequestBody AvailableProduct availableProduct){
        AvailableProduct availableProduct1=availableProductService.findById(availableProduct.getAvailableProductId());
        availableProduct.setCreatedAt(availableProduct1.getCreatedAt());
        return availableProductService.updateProduct(availableProduct);
    }
    @DeleteMapping("/delete/{AvailableProductId}")
    public String delete(@PathVariable int AvailableProductId){
        availableProductService.deleteAvailableProduct(AvailableProductId);
        return "Deleted AvailableProduct id - "+AvailableProductId;
    }
}
