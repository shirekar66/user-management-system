package com.scode.User_Management.service.impl;

import com.scode.User_Management.dto.ProductDto;
import com.scode.User_Management.entity.Product;
import com.scode.User_Management.exception.ProductNotFoundException;
import com.scode.User_Management.mapper.ProductMapper;
import com.scode.User_Management.repositories.ProductRepo;
import com.scode.User_Management.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        Product saved = productRepo.save(product);
        return ProductMapper.toDto(saved);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = getProductEntity(id);
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = getProductEntity(id);
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setActive(productDto.getActive());
        return ProductMapper.toDto(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductEntity(id);
        productRepo.delete(product);
    }

    private Product getProductEntity(Long id){
        return productRepo.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product Not found with id: " + id));
    }
}
