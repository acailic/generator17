package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SportShopBrand;


@Repository
public interface SportShopBrandRepository extends JpaRepository<SportShopBrand, Long> {


}
