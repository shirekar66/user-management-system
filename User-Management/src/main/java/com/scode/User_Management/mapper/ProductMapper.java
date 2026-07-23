package com.scode.User_Management.mapper;

import com.scode.User_Management.dto.ProductDto;
import com.scode.User_Management.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDto toDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getActive()
        );
    }

    public static Product toEntity(ProductDto dto) {


        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setActive(dto.getActive());

        return product;
    }
}