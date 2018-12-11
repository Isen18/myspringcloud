package com.isen.common.dto;

import java.io.Serializable;

/**
 * @author Isen
 * @date 2018/9/17 19:49
 * @since 1.0
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -598297175827072081L;

    private Integer id;
    private String name;

    public UserDTO(){};

    public UserDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
