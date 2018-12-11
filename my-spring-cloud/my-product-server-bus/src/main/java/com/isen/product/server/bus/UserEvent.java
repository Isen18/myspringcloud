package com.isen.product.server.bus;

import com.google.common.base.MoreObjects;
import com.isen.common.dto.UserDTO;
import com.isen.product.server.bus.util.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * 用户事件
 *
 * @author Isen
 * @date 2018/9/20 22:48
 * @since 1.0
 */
public class UserEvent extends ApplicationEvent {
    private Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    /** 消息类型：更新用户，值为: {@value} */
    public static final String ET_UPDATE = "update";

    private String action;
    private UserDTO user;

    public UserEvent(UserDTO user) {
        super(user);
        this.user = user;
    }

    public UserEvent(UserDTO user, String action) {
        super(user);
        this.action = action;
        this.user = user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("user", this.getUser()).toString();
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * 发布事件
     */
    public void fire() {
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        if(null != context) {
            logger.debug("发布事件：{}", this);
            context.publishEvent(this);
        }else{
            logger.warn("无法获取到当前Spring上下文信息，不能够发布事件");
        }
    }

}
