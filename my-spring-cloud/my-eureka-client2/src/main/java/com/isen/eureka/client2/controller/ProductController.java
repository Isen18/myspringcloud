package com.isen.eureka.client2.controller;

import com.isen.common.dto.ProductDTO;
import com.isen.eureka.client2.consumer.ProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/17 17:06
 * @since 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDTO> list() {
        return productService.findAll();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public ProductDTO detail(@PathVariable String itemCode) {
        return productService.loadByItemCode(itemCode);
    }
}