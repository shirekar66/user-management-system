package com.scode.User_Management.repositories;

import com.scode.User_Management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
