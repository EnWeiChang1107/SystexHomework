package com.systex.homework.service;

import com.systex.homework.dao.AvailableProductRepository;
import com.systex.homework.entity.AvailableProduct;
import com.systex.homework.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableProductServiceImpl implements AvailableProductService{
    private AvailableProductRepository availableProductRepository;
    private ProductService productService;
    @Autowired
    public AvailableProductServiceImpl(AvailableProductRepository availableProductRepository,ProductService productService){
        this.availableProductRepository=availableProductRepository;
        this.productService=productService;
    }

    @Override
    public List<AvailableProduct> findAll() {
        return availableProductRepository.findAll();
    }

    @Override
    public AvailableProduct findById(int id) {
        AvailableProduct availableProduct=availableProductRepository.findById(id).orElse(null);
        if(availableProduct==null){
            throw new NullPointerException("Did not find availableProduct id - "+id);
        }
        return availableProduct;
    }

    @Override
    public AvailableProduct addProduct(int id) {
        Product product=productService.findById(id);
        AvailableProduct availableProduct=new AvailableProduct();
        availableProduct.setName(product.getName());
        availableProduct.setPrice(product.getPrice());

        return availableProductRepository.save(availableProduct);
    }


    @Override
    public AvailableProduct updateProduct(AvailableProduct availableProduct) {

        return availableProductRepository.save(availableProduct);
    }

    @Override
    public void deleteAvailableProduct(int id) {
        AvailableProduct availableProduct=availableProductRepository.findById(id).orElse(null);
        if(availableProduct==null){
            throw new NullPointerException("Did not find availableProduct id - "+id);
        }
        availableProductRepository.delete(availableProduct);
    }
}
