package com.isen.product.server.bus.controller;

import com.isen.product.server.bus.UserEvent;
import com.isen.common.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/20 23:02
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 发布事件
     */
    @RequestMapping("/publicEvent")
    public void publicEvent(){
        UserDTO userDTO = new UserDTO(1, "isen");
        new UserEvent(userDTO, UserEvent.ET_UPDATE).fire();
    }
}
