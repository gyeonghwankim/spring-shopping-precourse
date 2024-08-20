package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.domain.ProductIdNameResponse;
import shopping.entity.Product;
import shopping.domain.ProductIdResponse;
import shopping.repository.ProductRepository;
import shopping.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public List<Product> selectProduct() {
        return productService.select();
    }

    @PostMapping("/api/products")
    public ProductIdResponse addProduct(@RequestBody String productName) {
        return productService.create(productName);
    }

    @PutMapping("/api/products/{name}")
    public ProductIdNameResponse updateProduct(@PathVariable String name, @RequestBody Product product) {
        return productService.update(name, product);
    }

    @DeleteMapping("/api/products/{name}")
    public String deleteProduct(@PathVariable String name) {
        return "";
    }
}
