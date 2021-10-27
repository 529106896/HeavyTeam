package com.example.exp_4.model;

import java.util.List;

public class ProductRetVo {

    private Integer id;

    private String name;

    private String skuSn;

    private String detail;

    private String imageUrl;

    private Integer originalPrice;

    private Integer price;

    private Integer inventory;

    private Integer state;

    private Integer weight;

    private String gmtCreate;

    private String gmtModified;

    private Boolean disable;

    private Boolean shareable;

    private List<GoodsRetVo> goodsRetVoList;

    public ProductRetVo(Product product) {

        this.id = product.getId();

        if(null != product.getName()) {
            this.name = product.getName();
        } else {
            this.name = "string";
        }

        this.skuSn = "string";

        if(null != product.getDetail()) {
            this.detail = product.getDetail();
        } else {
            this.detail = "string";
        }

        if(null != product.getImageUrl()) {
            this.imageUrl = product.getImageUrl();
        } else {
            this.imageUrl = "string";
        }

        if(null != product.getOriginalPrice()) {
            this.originalPrice = product.getOriginalPrice();
        } else {
            this.originalPrice = 0;
        }

        this.price = 0;

        this.inventory = 0;

        if(null != product.getStatus()) {
            this.state = product.getStatus().getCode();
        } else {
            this.state = 0;
        }

        if(null != product.getWeight()) {
            this.weight = product.getWeight();
        } else {
            this.weight = 0;
        }

        if(null != product.getGmtCreate()) {
            if(product.getGmtCreate().toString().contains("T")) {
                this.gmtCreate = product.getGmtCreate().toString().replaceAll("T", " ");
            } else {
                this.gmtCreate = product.getGmtCreate().toString();
            }
        } else {
            this.gmtCreate = "string";
        }

        if(null != product.getGmtModified()) {
            if(product.getGmtModified().toString().contains("T")) {
                this.gmtCreate = product.getGmtModified().toString().replaceAll("T", " ");
            } else {
                this.gmtCreate = product.getGmtModified().toString();
            }
        } else {
            this.gmtModified = "string";
        }

        if(null != product.getDisabled()) {
            this.disable = product.getDisabled();
        } else {
            this.disable = false;
        }

        this.shareable = false;
    }
}