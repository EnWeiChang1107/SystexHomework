package com.systex.homework.controller;

import com.systex.homework.entity.AvailableProduct;
import com.systex.homework.entity.Product;
import com.systex.homework.response.ErrorRes;
import com.systex.homework.service.AvailableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity getAll(){
        try {
            List<AvailableProduct> list = availableProductService.findAll();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

    @PostMapping("/add/{id}")
    public ResponseEntity add(@PathVariable int id){
        try {
            AvailableProduct availableProduct = availableProductService.addProduct(id);
            return new ResponseEntity<>(availableProduct, HttpStatus.CREATED);
        }catch (NullPointerException e){
            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Product NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

//    @PutMapping("/update")
//    public ResponseEntity<AvailableProduct> update(@RequestBody AvailableProduct availableProduct){
//        AvailableProduct availableProduct1=availableProductService.findById(availableProduct.getAvailableProductId());
//        availableProduct.setCreatedAt(availableProduct1.getCreatedAt());
//        AvailableProduct availableProduct2=availableProductService.updateProduct(availableProduct);
//        return ResponseEntity.ok().body(availableProduct2);
//    }
    @DeleteMapping("/delete/{AvailableProductId}")
    public ResponseEntity delete(@PathVariable int AvailableProductId){
        try {
            availableProductService.deleteAvailableProduct(AvailableProductId);
            return ResponseEntity.ok().body("Deleted AvailableProduct id - " + AvailableProductId);
        }catch (NullPointerException e){
            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Available Product NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
}
