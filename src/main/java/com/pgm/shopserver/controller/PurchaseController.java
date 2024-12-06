package com.pgm.shopserver.controller;

import com.pgm.shopserver.dto.PurchaseDTO;
import com.pgm.shopserver.security.UserPrinciple;
import com.pgm.shopserver.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Object> sevePurchase(@RequestBody PurchaseDTO purchaseDTO){
        log.info("PurchaseController purchaseDTO:"+purchaseDTO);
        return new ResponseEntity<>(purchaseService.savePurchase(purchaseDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllPurchasesOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+userPrinciple.getUser());
        return ResponseEntity.ok(purchaseService.findPurchaseItemsOfUser(userPrinciple.getUsername()));
    }
}
