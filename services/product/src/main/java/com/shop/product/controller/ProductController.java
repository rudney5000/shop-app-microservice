package com.shop.product.controller;

import com.shop.product.payload.rq.ProductPurchaseRequest;
import com.shop.product.payload.rq.ProductRequest;
import com.shop.product.payload.rs.ProductPurchaseResponse;
import com.shop.product.payload.rs.ProductResponse;
import com.shop.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createdProduct(
            @RequestBody @Valid ProductRequest productRequest
    ){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> purchaseRequests
    ){
        return ResponseEntity.ok(productService.purchaseProducts(purchaseRequests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
}
