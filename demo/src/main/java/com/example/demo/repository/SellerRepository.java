package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Seller;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


}
