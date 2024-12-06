package com.pgm.shopserver.dto;

import com.pgm.shopserver.domain.Product;
import com.pgm.shopserver.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {

    private Long id;

    private String username;

    private Long productId;

    private Integer quantity;

    private LocalDateTime purchaseTime;
}
