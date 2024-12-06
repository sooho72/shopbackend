package com.pgm.shopserver.dto;

import com.pgm.shopserver.domain.Product;
import com.pgm.shopserver.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Integer price;

    private LocalDateTime createTime;

}
