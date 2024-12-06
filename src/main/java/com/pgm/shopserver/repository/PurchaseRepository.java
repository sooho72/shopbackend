package com.pgm.shopserver.repository;

import com.pgm.shopserver.domain.Purchase;
import com.pgm.shopserver.repository.projection.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository <Purchase,Long >{
    @Query("select " +
            "prd.name as name, pur.quantity as quantity, pur.purchaseTime as purchaseTime " +
            "from Purchase pur left join Product prd on prd.id = pur.product.id " +
            "where pur.user.username = :username")
    List<PurchaseItem> findAllPurchasesOfUser(@Param("username") String username);


}
