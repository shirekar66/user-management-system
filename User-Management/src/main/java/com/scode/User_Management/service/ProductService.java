package com.scode.User_Management.service;

import com.scode.User_Management.dto.ProductDto;

import java.util.List;

public interface ProductService {

ProductDto addProduct(ProductDto productDto);

List<ProductDto> getAllProducts();

ProductDto getProductById(Long id);

ProductDto updateProduct(Long id, ProductDto productDto);

void deleteProduct(Long id);
}
