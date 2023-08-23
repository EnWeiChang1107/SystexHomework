package com.systex.homework.service;

import com.systex.homework.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(int id);

    List<Product> findAll();
    Product save(Product product);

    void delete(int id);
}
