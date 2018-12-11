package com.isen.product.server.bus.controller;

import com.isen.common.dto.ProductDTO;
import com.isen.product.server.bus.event.ProductService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/20 16:38
 * @since 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);

    @Resource
    ProductService productService;
    // 省略了其它不相干代码

    // TODO: 该端点仅仅是用来测试消息发送，并不包含任何业务逻辑处理
    @RequestMapping(value = "/{itemCode}", method = {RequestMethod.POST, RequestMethod.GET})
    public ProductDTO save(@PathVariable String itemCode) {
        ProductDTO productDto = this.productService.findOne(itemCode);
        if (null != productDto) {
            this.productService.save(productDto);
        }
        return productDto;
    }
}
