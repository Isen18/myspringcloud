package com.isen.resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isen.common.dto.FooDTO;
import java.io.IOException;
import java.net.URI;
import java.util.Set;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

/**
 * resttemplate的REST操作：POST/GET/PUT/DELETE
 *
 * @author Isen
 * @date 2018/9/17 20:35
 * @since 1.0
 */
public class Demo {

    private static final String POST_URL = "http://localhost:8080/foos/post/";
    private static final String GET_URL = "http://localhost:8080/foos/get/1";
    private static final String PUT_URL = "http://localhost:8080/foos/put/";
    private static final String DELETE_URL = "http://localhost:8080/foos/delete/";


    /**
     * 获取json
     */
    public void getJson() throws IOException {
        RestTemplate restTempalte = new RestTemplate();
        ResponseEntity<String> response = restTempalte.getForEntity(GET_URL, String.class);
        assert HttpStatus.OK.equals(response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("name");
        System.out.println("name=" + name);
    }

    /**
     * 获取pojo对象
     */
    public void getObject(){
        RestTemplate  restTempalte = new RestTemplate();
        FooDTO fooDTO = restTempalte.getForObject(GET_URL, FooDTO.class);
        System.out.println(fooDTO);
    }

    /**
     * 获取header
     */
    public void getHeader(){
        RestTemplate  restTempalte = new RestTemplate();
        HttpHeaders httpHeaders = restTempalte.headForHeaders(GET_URL);
        System.out.println(httpHeaders.getHost());
    }

    /**
     * post object
     */
    public void postForObject(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FooDTO> request = new HttpEntity<>(new FooDTO(6L, "bar", 3, 3));
        FooDTO foo = restTemplate.postForObject(POST_URL + "postForObject", request, FooDTO.class);
        System.out.println(foo);
    }

    /**
     * post location
     */
    public void postForLocation(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FooDTO> request = new HttpEntity<>(new FooDTO(6L, "bar", 3, 3));
        URI location = restTemplate.postForLocation(POST_URL + "postForLocation", request);
        System.out.println(location);
    }

    /**
     * 交换对象
     */
    public void exchange(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FooDTO> request = new HttpEntity<>(new FooDTO(6L, "bar", 3, 3));
        ResponseEntity<FooDTO> response = restTemplate.exchange(POST_URL + "exchange", HttpMethod.POST, request, FooDTO.class);

        assert HttpStatus.OK.equals(response.getStatusCode());
        FooDTO fooDTO = response.getBody();
        System.out.println(fooDTO);
    }

    /**
     * 获取允许执行的操作列表
     */
    public void optionsForAllow(){
        RestTemplate restTemplate = new RestTemplate();
        Set<HttpMethod> optionsForAllow  = restTemplate.optionsForAllow(GET_URL);
        optionsForAllow.forEach(option -> System.out.println(option));
    }

    /**
     * 简单put
     */
    public void simplePut(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FooDTO> requestEntity = new HttpEntity<>(new FooDTO(7L, "bar", 3, 3));
        ResponseEntity<FooDTO> responseEntity = restTemplate.exchange(PUT_URL, HttpMethod.PUT, requestEntity, FooDTO.class);
        assert HttpStatus.OK.equals(responseEntity.getStatusCode());

        FooDTO newFooDTO = responseEntity.getBody();
        System.out.println("newFooDTO=" + newFooDTO);
    }

    /**
     * 简单put2
     */
    public void simplePut2(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FooDTO> requestEntity = new HttpEntity<>(new FooDTO(8L, "bar", 3, 3));
        restTemplate.put(PUT_URL, requestEntity);
    }

    /**
     * 带回调的put
     */
    public void putWithCallback(){
        RestTemplate restTemplate = new RestTemplate();
        FooDTO fooDTO = new FooDTO(9L, "bar", 3, 3);
        restTemplate.execute(PUT_URL, HttpMethod.PUT, requestCallback(fooDTO), clientHttpResponse -> null);
    }

    /**
     * 删除
     */
    public void delete(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_URL + "2");
    }


    /**
     * 回调函数
     */
    private RequestCallback requestCallback(final FooDTO fooDTO){
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), fooDTO);
            clientHttpRequest.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic fooDTO");
        };
    }
}
