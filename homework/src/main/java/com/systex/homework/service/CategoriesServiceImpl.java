package com.systex.homework.service;

import com.systex.homework.dao.CategoriesRepository;
import com.systex.homework.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriesServiceImpl implements CategoriesService{
    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository){
        this.categoriesRepository=categoriesRepository;
    }
    @Override
    public Categories findById(int id) {
        Categories categories = categoriesRepository.findById(id).orElse(null);
        if(categories==null){
            throw new NullPointerException("Did not find category id - " + id);
        }
        return categories;
    }

    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public void delete(int id) {
        Categories categories=categoriesRepository.findById(id).orElse(null);

        categoriesRepository.delete(categories);

    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }
}
