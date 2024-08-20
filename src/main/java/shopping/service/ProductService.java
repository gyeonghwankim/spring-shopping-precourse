package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.domain.ProductIdResponse;
import shopping.domain.ProductMsgResponse;
import shopping.entity.Product;
import shopping.repository.ProductRepository;

import java.util.List;
import java.util.Random;

@Transactional
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // 생성, 수정, 삭제

    public List<Product> select() {
        return productRepository.findAll();
    }

    public ProductIdResponse create(String productName) {
        if (productName == null || productName.isEmpty()) {
            System.out.println("누락된 파라미터가 있습니다.");
            return new ProductIdResponse(0L);
        }

        List<Product> products = productRepository.findAll();

        // 이미 존재하는 상품 조회 예외 케이스
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                System.out.println("이미 존재하는 상품입니다.");
                return new ProductIdResponse(0L);
            }
        }

        Random rand = new Random();
        Long tmpId = rand.nextLong(100000) + 1;
        Product newProduct = new Product(tmpId, productName, 0, null);

        products.add(newProduct);
        ProductIdResponse result = new ProductIdResponse(tmpId);

        return result;
    }

    public ProductMsgResponse delete(String productName) {
        Product product = productRepository.findByName(productName);
        if (product == null) {
            return new ProductMsgResponse("조회된 상품이 없습니다.");
        }
        productRepository.delete(product);
        return null;
    }
}
