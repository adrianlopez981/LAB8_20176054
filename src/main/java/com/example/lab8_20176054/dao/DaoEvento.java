package com.example.lab8_20176054.dao;

import com.example.lab8_20176054.Entity.empresa;
import com.example.lab8_20176054.Entity.evento;
import com.example.lab8_20176054.dto.EventoDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class DaoEvento {




    public List<evento> listarEventos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<evento[]> response = restTemplate.getForEntity(
                "http://localhost:8085/evento", evento[].class);

        return Arrays.asList(response.getBody());
    }

    public evento buscarPorId(int id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8085/product/" + id;

        ResponseEntity<EventoDto> responseMap = restTemplate.getForEntity(url, EventoDto.class);

        EventoDto eventoDto = responseMap.getBody();


        return eventoDto.getEvento();
    }







    //    public Product obtenerProductoPorId(int id) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String url = "http://localhost:8080/product/" + id;
//
//        ResponseEntity<ProductDto> responseMap = restTemplate.getForEntity(url, ProductDto.class);
//
//        ProductDto productDto = responseMap.getBody();
//
//        return productDto.getProduct();




//    public List<evento> listarProductosBasicAuth() {
//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .basicAuthentication("oscar.diaz@gmail.com", "oscar.diaz")
//                .build();
//        ResponseEntity<evento[]> response = restTemplate.getForEntity(
//                "http://localhost:8080/api/product", evento[].class);
//
//        return Arrays.asList(response.getBody());
//    }
//
//    public void guardarProducto(Product product) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String url = "http://localhost:8080/product";
//        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        if (product.getId() > 0) {
//            restTemplate.put(url, httpEntity, Product.class);
//        } else {
//            restTemplate.postForEntity(url, httpEntity, Product.class);
//        }
//
//    }
//
//    public Product obtenerProductoPorId(int id) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String url = "http://localhost:8080/product/" + id;
//
//        ResponseEntity<ProductDto> responseMap = restTemplate.getForEntity(url, ProductDto.class);
//
//        ProductDto productDto = responseMap.getBody();
//
//        return productDto.getProduct();
//    }
//
//    public void borrarProducto(int id) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete("http://localhost:8080/product/" + id);
//    }


}
