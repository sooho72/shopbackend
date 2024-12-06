package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Product;
import com.pgm.shopserver.dto.ProductDTO;
import com.pgm.shopserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public Long saveProduct(ProductDTO productDTO) {
        Product product =dtoToProduct(productDTO);
        product.setCreateTime(LocalDateTime.now());
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<ProductDTO> findAllProduct() {
        List<Product> productS = productRepository.findAll();
        List<ProductDTO> productDTOS = productS.stream()
                .map(product -> entityToDto(product)).collect(Collectors.toList());
        return productDTOS;
    }
}
