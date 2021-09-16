package com.rpint.productservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rpint.productservice.dto.ProductResDto;
import com.rpint.productservice.dto.ProductServiceRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Map;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000))
                .build();
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Override
    public ProductServiceRes getProductDetails(String productId) {
        ProductServiceRes productServiceRes = new ProductServiceRes();
        productServiceRes.setProductResDto(getByRestTemplate(productId));
        productServiceRes.setAdidasRes(adidasResByWebClientBuilder(productId));
        return productServiceRes;
    }

    private Map<String, Object> adidasResByWebClientBuilder(String productId) {
        String url = ADIDAS_UK_URL + productId;
        log.debug("adidas service url is : ", url);
        String adidasRes = builder.build().get().uri(ADIDAS_UK_URL + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return transformToJson(adidasRes);
    }

    private static Map<String, Object> transformToJson(String adidasRes) {
        GsonJsonParser gsonJsonParser = new GsonJsonParser();
        Map<String, Object> stringObjectMap = gsonJsonParser.parseMap(adidasRes);
        return stringObjectMap;
    }

    private ProductResDto getByRestTemplate(String productId) {
        return restTemplate.getForObject("http://PRODUCT-REVIEW/review/" + productId, ProductResDto.class);
    }

    private ProductResDto getByWebClientBuilder(String productId) {
        return builder.build().get().uri("http://PRODUCT-REVIEW/review/" + productId)
                .retrieve()
                .bodyToMono(ProductResDto.class)
                .block();
    }
}
