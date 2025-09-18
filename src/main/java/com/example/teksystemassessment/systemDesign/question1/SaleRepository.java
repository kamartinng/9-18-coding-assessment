package com.example.teksystemassessment.systemDesign.question1;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query(value = """
        SELECT p.product_id AS productId,
        p.name AS name,
        COALESCE(SUM(s.quantity_sold), 0) AS totalSold
        FROM products p
        LEFT JOIN sales s ON s.product_id = p.product_id
        GROUP BY p.product_id, p.name
        ORDER BY totalSold DESC, p.product_id ASC
    """,
        nativeQuery = true
    )
    List<TopSellerProjection> findTopSellers(Pageable pageable);
}
