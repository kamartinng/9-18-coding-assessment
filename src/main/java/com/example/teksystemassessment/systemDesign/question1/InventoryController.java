package com.example.teksystemassessment.systemDesign.question1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestParam int productId,
                                           @RequestParam String name,
                                           @RequestParam double price,
                                           @RequestParam int stock) {
        service.addProduct(productId, name, price, stock);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sales")
    public ResponseEntity<String> recordSale(@RequestParam int productId,
                                             @RequestParam int quantitySold) {
        boolean ok = service.recordSale(productId, quantitySold);
        return ok ? ResponseEntity.ok("recorded")
                : ResponseEntity.badRequest().body("out of stock or invalid product");
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<String>> topSelling() {
        return ResponseEntity.ok(service.topSellingProducts());
    }
}
