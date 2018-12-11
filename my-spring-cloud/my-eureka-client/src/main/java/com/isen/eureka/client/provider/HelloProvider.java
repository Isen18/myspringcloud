package com.isen.eureka.client.provider;

import com.netflix.appinfo.EurekaInstanceConfig;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/17 15:28
 * @since 1.0
 */
@RestController
@RequestMapping("/HelloProvider")
public class HelloProvider {
    private Logger logger = LoggerFactory.getLogger(HelloProvider.class);

    @Value("${service.port}")
    String port;

    @Resource
    private EurekaInstanceConfig eurekaInstanceConfig;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "isen") String name){
        logger.info("/HelloProvider/hello, instanceId={}, host={}, name={}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false), name);
        return "hello, " +  name + ", i am from port:" + port;
    }
}
