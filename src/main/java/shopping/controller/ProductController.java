package shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.domain.Product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>(){
        {
            put(8146027L, new Product("아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        }
    };

    @GetMapping("/api/products")
    public List<Product> selectProduct() {
        List<Product> list = products.entrySet().stream().map(e -> new Product(e.getKey(), e.getValue().getName(), e.getValue().getPrice(), e.getValue().getImageUrl())).toList();
        return list;
    }

}
