package com.isen.common.dto;

import java.io.Serializable;

/**
 * @author Isen
 * @date 2018/9/17 23:44
 * @since 1.0
 */
public class FooDTO implements Serializable {

    private static final long serialVersionUID = 1697880927322603075L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格(分)
     */
    private Integer price;

    /**
     * 数量
     */
    private Integer cnt;

    public FooDTO(){};

    public FooDTO(Long id, String name, Integer price, Integer cnt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cnt = cnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "FooDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cnt=" + cnt +
                '}';
    }
}
