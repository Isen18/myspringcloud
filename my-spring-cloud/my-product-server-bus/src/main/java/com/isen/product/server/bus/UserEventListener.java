package com.isen.product.server.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户事件监听器
 *
 * @author Isen
 * @date 2018/9/20 22:52
 * @since 1.0
 */
@Component
public class UserEventListener implements ApplicationListener<UserEvent> {
    private Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        this.logger.debug("收到用户事件:{} ", userEvent);
        // TODO: 实现具体的业务处理
    }
}
