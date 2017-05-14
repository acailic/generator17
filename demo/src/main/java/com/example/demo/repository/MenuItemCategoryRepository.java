package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MenuItemCategory;


@Repository
public interface MenuItemCategoryRepository extends JpaRepository<MenuItemCategory, Long> {


}
