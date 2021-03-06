package com.isen.product.server.bus.event;

import com.isen.product.server.bus.util.ApplicationContextHolder;
import com.isen.common.dto.ProductDTO;
import com.isen.common.mq.ProductMsg;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Isen
 * @date 2018/9/20 23:09
 * @since 1.0
 */
@Service
public class ProductService {
    protected Logger logger = LoggerFactory.getLogger(ProductService.class);

    private List<ProductDTO> productList;

    @Autowired
    public ProductService() {
        this.productList = this.buildProducts();
    }


    /**
     * 根据ItemCode获取
     * @param itemCode
     * @return
     */
    public ProductDTO findOne(String itemCode) {
        for (ProductDTO productDto : this.productList) {
            if (productDto.getItemCode().equalsIgnoreCase(itemCode)){
                return productDto;
            }
        }

        return null;
    }

    /**
     * 保存或更新商品信息
     * @param productDto
     * @return
     */
    public ProductDTO save(ProductDTO productDto) {
        // TODO: 实现商品保存处理
        for (ProductDTO sourceProductDto : this.productList) {
            if (sourceProductDto.getItemCode().equalsIgnoreCase(productDto.getItemCode())) {
                sourceProductDto.setName(sourceProductDto.getName() + "-new");
                sourceProductDto.setPrice(sourceProductDto.getPrice() + 100);
                productDto = sourceProductDto;
                break;
            }
        }

        // 发送商品消息
        // this.sendMsg(ProductMsg.MA_UPDATE, productDto.getItemCode());
        // 发布商品变更消息
        this.fireEvent(ProductEvent.ET_UPDATE, productDto);

        return productDto;
    }

    // 这里已不再使用该方法
    protected void sendMsg(String msgAction, String itemCode) {
        ProductMsg productMsg = new ProductMsg(msgAction, itemCode);
        this.logger.info("发送商品消息:{} ", productMsg);

        // 发送消息
        // this.source.output().send(MessageBuilder.withPayload(productMsg).build());
    }

    protected void fireEvent(String eventAction, ProductDTO productDto) {
        ProductEvent productEvent = new ProductEvent(productDto,
                ApplicationContextHolder.getApplicationContext().getId(), "my-mall-server-bus:**",
                eventAction, productDto.getItemCode());
//        ProductEvent productEvent = new ProductEvent(productDto,
//                ApplicationContextHolder.getApplicationContext().getId(), "*:**",
//                eventAction, productDto.getItemCode());

        // 发布事件
        RemoteApplicationEventPublisher.publishEvent(productEvent);
    }

    protected List<ProductDTO> buildProducts() {
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO("item-1", "测试商品-1", "TwoStepsFromJava", 100));
        products.add(new ProductDTO("item-2", "测试商品-2", "TwoStepsFromJava", 200));
        products.add(new ProductDTO("item-3", "测试商品-3", "TwoStepsFromJava", 300));
        products.add(new ProductDTO("item-4", "测试商品-4", "TwoStepsFromJava", 400));
        products.add(new ProductDTO("item-5", "测试商品-5", "TwoStepsFromJava", 500));
        products.add(new ProductDTO("item-6", "测试商品-6", "TwoStepsFromJava", 600));
        return products;
    }
}
