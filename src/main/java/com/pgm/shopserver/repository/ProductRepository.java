package com.pgm.shopserver.repository;

import com.pgm.shopserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
