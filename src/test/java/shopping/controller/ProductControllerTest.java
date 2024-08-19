package shopping.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

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
}