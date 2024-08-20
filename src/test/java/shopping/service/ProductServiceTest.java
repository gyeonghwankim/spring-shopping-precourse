package shopping.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shopping.entity.Product;
import shopping.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class ProductServiceTest {
    private ProductService productService;
    private final ProductRepository productRepository = mock(ProductRepository.class);

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void select() {
        // given
        List<Product> list = new ArrayList<>();
        given(productRepository.findAll()).willReturn(list);

        // when
        productService.select();

        // then
        then(productRepository).should().findAll();
    }

    @Test
    void create() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("test");
        given(productRepository.save(product)).willReturn(product);

        productService.create("test");

        then(productRepository).should().save(any());
    }

    @Test
    void update() {
        String name = "test";
        Product product = new Product();

        given(productRepository.findByName(any(String.class))).willReturn(product);

        productService.update(name, product);

        then(productRepository).should(times(1)).findByName(any());
    }

    @Test
    void delete() {
        given(productRepository.findByName(any())).willReturn(new Product());

        productService.delete("test");

        then(productRepository).should().delete(any());
    }
}