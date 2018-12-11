package com.isen.eureka.client2.controller;

import com.isen.common.dto.UserDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Isen
 * @date 2018/9/17 19:49
 * @since 1.0
 */
@RestController
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="findByIdFallback")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDTO findById(@PathVariable Integer id) {
        return restTemplate.getForObject("http://my-eureka-client/"+ id, UserDTO.class);
    }

    public UserDTO findByIdFallback(Integer id) {
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setName("back name");
        return user;
    }
}
