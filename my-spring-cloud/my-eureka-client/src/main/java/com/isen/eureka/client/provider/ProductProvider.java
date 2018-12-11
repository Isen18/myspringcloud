package com.isen.eureka.client.provider;

import com.isen.common.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/17 16:46
 * @since 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductProvider {
    private Logger logger = LoggerFactory.getLogger(ProductProvider.class);
    private static List<ProductDTO> products = new ArrayList<>();
    static {
        products.add(new ProductDTO("item-1", "测试商品-1", "品牌名称", 100));
        products.add(new ProductDTO("item-2", "测试商品-2", "品牌名称", 200));
        products.add(new ProductDTO("item-3", "测试商品-3", "品牌名称", 300));
        products.add(new ProductDTO("item-4", "测试商品-4", "品牌名称", 400));
        products.add(new ProductDTO("item-5", "测试商品-5", "品牌名称", 500));
        products.add(new ProductDTO("item-6", "测试商品-6", "品牌名称", 600));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDTO> list() {
        return products;
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public ProductDTO detail(@PathVariable String itemCode) {
        for (ProductDTO product : products) {
            if (product.getItemCode().equalsIgnoreCase(itemCode)){
                return product;
            }
        }
        return null;
    }
}
