package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Product;
import com.pgm.shopserver.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Long saveProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> findAllProduct();

    default Product dtoToProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .build();
        return product;
    }
    default ProductDTO entityToDto(Product product) {
        ProductDTO productDTO = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .createTime(product.getCreateTime())
                .build();
        return productDTO;
    }
}
