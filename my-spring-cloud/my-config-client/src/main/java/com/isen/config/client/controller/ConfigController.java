package com.isen.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/19 17:42
 * @since 1.0
 */
@RestController
@RequestMapping("/cfg")
public class ConfigController {

    @Value("${foo}")
    String foo;

    @Value("${bar}")
    String bar;

    @RequestMapping(value = "/foo")
    public String foo(){
        return foo + "==" + bar;
    }
}
