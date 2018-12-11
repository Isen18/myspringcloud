package com.isen.common.dto;

import java.io.Serializable;

/**
 * @author Isen
 * @date 2018/9/17 16:47
 * @since 1.0
 */
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = -5965359935308634003L;
    /**
     * 产品货号
     */
    private String itemCode;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品品牌名称
     */
    private String bandName;

    /**
     * 产品价格(分)
     */
    private int price;

    public ProductDTO(){};

    public ProductDTO(String itemCode, String name, String bandName, int price) {
        this.itemCode = itemCode;
        this.name = name;
        this.bandName = bandName;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", bandName='" + bandName + '\'' +
                ", price=" + price +
                '}';
    }
}
