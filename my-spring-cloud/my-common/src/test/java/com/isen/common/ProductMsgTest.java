package com.isen.common;

import com.isen.common.mq.ProductMsg;
import org.junit.Test;

/**
 * @author Isen
 * @date 2018/9/20 18:48
 * @since 1.0
 */
public class ProductMsgTest {
    @Test
    public void tsetToString(){
        ProductMsg productMsg = new ProductMsg();
        productMsg.setAction(ProductMsg.MA_UPDATE);
        productMsg.setItemCode("item-3");
        System.out.println(productMsg);
    }
}
