package shopping.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import shopping.entity.Product;

//@TestPropertySource(locations = "classpath:application-test.yaml")
@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void save() {
        Product product = new Product(1L, "test", 5000, "image");
        productRepository.save(product);
    }
}