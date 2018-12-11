package com.isen.user.server.controller;

import com.isen.common.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/17 20:26
 * @since 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${service.port:2200}")
    private int serverPort = 2200;

    @RequestMapping(value = "/{loginName}", method = RequestMethod.GET)
    public UserDTO detail(@PathVariable String loginName) {
        String memos = "I come form " + serverPort;
        loginName += memos;
        System.out.println(loginName);
        return new UserDTO(1, loginName);
    }
}
