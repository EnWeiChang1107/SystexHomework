package com.systex.homework.service;

import com.systex.homework.dao.ProductRepository;
import com.systex.homework.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public Product findById(int id) {
        Product product=productRepository.findById(id).orElse(null);
        if(product==null){
            throw new NullPointerException();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        Product product=productRepository.findById(id).orElse(null);

        productRepository.deleteById(id);
    }
}
