package com.systex.homework.service;

import com.systex.homework.entity.Categories;

import java.util.List;

public interface CategoriesService {
    Categories findById(int id);

    Categories save(Categories categories);

    void delete(int id);

    List<Categories> findAll();


}
