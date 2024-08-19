package shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shopping.domain.Product;
import shopping.domain.ProductIdResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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




    @PostMapping("/api/products")
    public ProductIdResponse addProduct(@RequestBody String productName) {
        if (productName == null || productName.isEmpty()) {
            System.out.println("누락된 파라미터가 있습니다.");
            return new ProductIdResponse(0L);
        }

        // 이미 존재하는 상품 조회 예외 케이스
        for (Product product : products.values()) {
            if (product.getName().equals(productName)) {
                System.out.println("이미 존재하는 상품입니다.");
                return new ProductIdResponse(0L);
            }
        }

        Random rand = new Random();
        Long tmpId = rand.nextLong(100000) + 1;
        Product newProduct = new Product(tmpId, productName, 0, null);

        products.put(tmpId, newProduct);

        ProductIdResponse result = new ProductIdResponse(tmpId);
        return result;
    }

}
