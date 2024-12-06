package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Product;
import com.pgm.shopserver.domain.Purchase;
import com.pgm.shopserver.domain.User;
import com.pgm.shopserver.dto.PurchaseDTO;
import com.pgm.shopserver.repository.ProductRepository;
import com.pgm.shopserver.repository.PurchaseRepository;
import com.pgm.shopserver.repository.UserRepository;
import com.pgm.shopserver.repository.projection.PurchaseItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class purchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Purchase savePurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase=Purchase.builder()
                .quantity(purchaseDTO.getQuantity())
                .build();
        User user=userRepository.findByUsername(purchaseDTO.getUsername());
        Product product=productRepository.findById(purchaseDTO.getProductId()).orElseThrow();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }
    @Override
    public List<PurchaseItem>  findPurchaseItemsOfUser(String username) {
        User user=userRepository.findByUsername(username);
        return purchaseRepository.findAllPurchasesOfUser(username);

    }

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);

    }

}

