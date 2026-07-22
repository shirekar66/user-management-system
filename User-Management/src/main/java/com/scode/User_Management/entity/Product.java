/*
package com.scode.User_Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 200)
    private String name;

    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active=true;



}
*/
