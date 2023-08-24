package com.systex.homework.service;

import com.systex.homework.entity.AvailableProduct;
import com.systex.homework.entity.Product;

import java.util.List;

public interface AvailableProductService {
    List<AvailableProduct> findAll();
    AvailableProduct findById(int id);

    AvailableProduct addProduct(int id);

    AvailableProduct updateProduct(AvailableProduct availableProduct);

    void deleteAvailableProduct(int id);
}
