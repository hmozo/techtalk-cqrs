package com.techtalk.productsservice.infrastructure.repositories.jpa;

import com.techtalk.productsservice.domain.ProductRepository;
import com.techtalk.productsservice.domain.projection.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;

    public ProductRepositoryImpl(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productJPARepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productJPARepository.findAll();
    }
}
