package shopping.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import shopping.entity.Product;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestClient.Builder clientBuilder;
    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = clientBuilder.build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectProduct() {
        String body = restClient.get()
                .uri("http://localhost:" + port + "/api/products")
                .retrieve()
                .body(String.class);

        System.out.println(body);
        Assertions.assertThat(body).isEqualTo("[{\"id\":8146027,\"name\":\"아이스 카페 아메리카노 T\",\"price\":4500,\"imageUrl\":\"https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg\"}]");
    }

    @Test
    void addProduct() {
        Product product = new Product("카페라떼", 0, null);

        ResponseEntity<Void> bodilessEntity = restClient.post()
                .uri("http://localhost:" + port + "/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .toBodilessEntity();

        System.out.println(bodilessEntity.getBody());
        //Assertions.assertThat(bodilessEntity.getBody());
    }
}