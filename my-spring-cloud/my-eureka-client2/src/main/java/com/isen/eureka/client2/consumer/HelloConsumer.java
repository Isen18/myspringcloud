package com.isen.eureka.client2.consumer;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Isen
 * @date 2018/9/17 15:34
 * @since 1.0
 */
@RestController
@RequestMapping("/HelloConsumer")
public class HelloConsumer {
    private Logger logger = LoggerFactory.getLogger(HelloConsumer.class);

    @Value("${service.port}")
    String port;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "isen") String name){
        logger.info("/HelloConsumer/hello, name={}", name);
        return restTemplate.getForEntity("http://my-eureka-client/HelloProvider/hello", String.class).getBody();
    }
}
