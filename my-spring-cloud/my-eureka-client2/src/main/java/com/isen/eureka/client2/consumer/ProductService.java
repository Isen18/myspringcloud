package com.isen.eureka.client2.consumer;

import com.isen.common.dto.ProductDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Isen
 * @date 2018/9/17 16:58
 * @since 1.0
 */
@FeignClient(name = "my-eureka-client", fallback = ProductServiceFallback.class)
public interface ProductService {

    //对应服务my-eureka-client对应的接口
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<ProductDTO> findAll();

    @RequestMapping(value = "/products/{itemCode}", method = RequestMethod.GET)
    ProductDTO loadByItemCode(@PathVariable("itemCode") String itemCode);

//    @RequestMapping(value = "/products/detail", method = RequestMethod.GET)
//    ProductDTO loadByItemCode2(@RequestParam("itemCode") String itemCode);
//
//    @RequestMapping(value = "/products/detail", method = RequestMethod.GET)
//    ProductDTO loadByItemCode3(@RequestHeader("itemCode") String itemCode);
}