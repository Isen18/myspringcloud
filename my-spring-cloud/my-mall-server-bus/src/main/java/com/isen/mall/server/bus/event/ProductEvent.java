package com.isen.mall.server.bus.event;

import com.google.common.base.MoreObjects;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @author Isen
 * @date 2018/9/20 23:06
 * @since 1.0
 */
public class ProductEvent extends RemoteApplicationEvent {
    /** 消息类型：更新商品，值为: {@value} */
    public static final String ET_UPDATE = "update";
    /** 消息类型：删除商品，值为: {@value} */
    public static final String ET_DELETE = "delete";

    private String action;
    private String itemCode;

    public ProductEvent() {
        super();
    }

    public ProductEvent(Object source, String originService, String destinationService, String action, String itemCode) {
        super(source, originService, destinationService);
        this.action = action;
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("itemCode", this.getItemCode()).toString();
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
