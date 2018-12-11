package com.isen.product.server.bus;

import com.isen.product.server.bus.util.ApplicationContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * @author Isen
 * @date 2018/9/20 17:09
 * @since 1.0
 */
@EnableDiscoveryClient
@RemoteApplicationEventScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
