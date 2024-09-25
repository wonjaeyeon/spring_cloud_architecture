package msa.ecommerce.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.product.exception.ProductPurchaseException;
import msa.ecommerce.product.repository.ProductRepository;
import msa.ecommerce.product.request.ProductPurchaseRequest;
import msa.ecommerce.product.request.ProductPurchaseResponse;
import msa.ecommerce.product.request.ProductRequest;
import msa.ecommerce.product.request.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequestList) {
        System.out.println("productPurchaseRequestList = " + productPurchaseRequestList);

        var productIds = productPurchaseRequestList.stream()
                .map(ProductPurchaseRequest::product_id)
                .toList();

        System.out.println("productIds = " + productIds);

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (storedProducts.size() != productIds.size()) {
            System.out.println("storedProducts.size() = " + storedProducts.size());
            System.out.println("productIds.size() = " + productIds.size());
            throw new ProductPurchaseException("Some products don't exist on Database");
        }

        var storedRequest = productPurchaseRequestList
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::product_id))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var request = storedRequest.get(i);

            if (storedProduct.getAvailableQuantity() < request.quantity()) {
                throw new ProductPurchaseException("Not enough quantity for product with id: " + storedProduct.getId());
            }

            var newAvailableQuantity = storedProduct.getAvailableQuantity() - request.quantity();
            storedProduct.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(storedProduct);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(storedProduct, request.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
