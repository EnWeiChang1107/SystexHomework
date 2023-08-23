package com.systex.homework.service;

import com.systex.homework.entity.Product;

public interface ProductService {
    Product findById(int id);

    Product save(Product product);

    void delete(int id);
}
