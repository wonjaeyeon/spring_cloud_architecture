package msa.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.product.request.ProductRequest;
import msa.ecommerce.product.request.ProductPurchaseRequest;
import msa.ecommerce.product.request.ProductPurchaseResponse;
import msa.ecommerce.product.request.ProductResponse;
import msa.ecommerce.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> productPurchaseRequestList){
        return ResponseEntity.ok(productService.purchaseProduct(productPurchaseRequestList));
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product_id") Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

}
