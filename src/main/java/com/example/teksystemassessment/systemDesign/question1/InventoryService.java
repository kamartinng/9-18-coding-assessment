package com.example.teksystemassessment.systemDesign.question1;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public InventoryService(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Transactional
    public void addProduct(int productId, String name, double price, int stock) {
        Product newProduct = new Product(productId, name, BigDecimal.valueOf(price), stock);
        productRepository.save(newProduct);
    }

    public boolean recordSale(int productId, int quantitySold) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return false;
        if (product.getStock() < quantitySold) return false;

        product.setStock(product.getStock() - quantitySold);
        productRepository.save(product);
        Sale sale = new Sale(product, quantitySold);
        saleRepository.save(sale);

        return true;
    }

    @Transactional
    public List<String> topSellingProducts() {
        return saleRepository.findTopSellers(PageRequest.of(0, 3))
                .stream()
                .map(product -> product.getName() + "," + product.getTotalSold())
                .toList();
    }
}
