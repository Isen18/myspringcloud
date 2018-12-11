package com.isen.eureka.client2.consumer;

import com.isen.common.dto.ProductDTO;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 发生错误时的备选方案
 *
 * @author Isen
 * @date 2018/9/17 19:30
 * @since 1.0
 */
@Component
public class ProductServiceFallback implements ProductService {

    @Override
    public List<ProductDTO> findAll() {
        return Collections.emptyList();
    }

    @Override
    public ProductDTO loadByItemCode(String itemCode) {
        return new ProductDTO("error", "未知", "商品品牌", 0);
    }
}
