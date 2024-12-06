package com.pgm.shopserver.controller;

import com.pgm.shopserver.dto.ProductDTO;
import com.pgm.shopserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDTO productDTO){
        log.info(productDTO);
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        System.out.println("getList");
        return new ResponseEntity<>(productService.findAllProduct(),HttpStatus.OK);
    }
    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
