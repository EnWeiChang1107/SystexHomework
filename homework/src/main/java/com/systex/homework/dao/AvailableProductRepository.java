package com.systex.homework.dao;

import com.systex.homework.entity.AvailableProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableProductRepository extends JpaRepository<AvailableProduct,Integer> {
}
