package com.isen.eureka.client2.consumer;

import java.net.URI;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Isen
 * @date 2018/9/17 16:11
 * @since 1.0
 */
@RestController
@RequestMapping("/HelloConsumer2")
public class HelloConsumer2 {
    private Logger logger = LoggerFactory.getLogger(HelloConsumer2.class);

    @Value("${service.port}")
    String port;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hello2")
    public String hello2() {
        ServiceInstance instance = loadBalancerClient.choose("my-eureka-client");
        URI helloUri = URI.create(String.format("http://%s:%s/HelloProvider/hello", instance.getHost(), instance.getPort()));
        logger.info("Target service uri = {}. ", helloUri.toString());
        return new RestTemplate().getForEntity(helloUri, String.class).getBody();
    }
}
