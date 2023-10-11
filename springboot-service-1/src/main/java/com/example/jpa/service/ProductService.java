package com.example.jpa.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.jpa.dto.Product;

@Service
public class ProductService {

	private final RestClient restClient;

    public ProductService() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/api")
                .build();
    }

	public Product addProduct(Product product) {
		return restClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);

	}

	public Product getProduct(int id) {
		return restClient.get()
                .uri("/products/find/{id}",id)
                .retrieve()
                .body(Product.class);
	}

	public List<Product> getProducts() {
		return restClient.get()
                .uri("/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>(){});
	}

	public Product updateProduct(int id, Product product) {
		return restClient.put()
	             .uri("/products/{id}",id)
	             .contentType(MediaType.APPLICATION_JSON)
	             .body(product)
	             .retrieve()
	             .body(Product.class);
	}

	public String deleteProduct(int id) {
		restClient.delete()
        .uri("/products/{id}",id)
        .retrieve()
        .toBodilessEntity();
		return "product removed : "+id;
	}

}