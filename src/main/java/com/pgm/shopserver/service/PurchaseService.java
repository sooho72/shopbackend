package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Purchase;
import com.pgm.shopserver.dto.PurchaseDTO;
import com.pgm.shopserver.repository.projection.PurchaseItem;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(PurchaseDTO purchaseDTO);
    List<PurchaseItem>  findPurchaseItemsOfUser(String username);
    List<Purchase> findAllPurchases();
    void deletePurchase(Long id);
}
