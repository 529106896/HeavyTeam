package com.example.exp_2.model;

import lombok.Data;

@Data
public class OrderItemRetVo {
    private Integer skuId;

    private Integer orderId;

    private String name;

    private Integer quantity;

    private Integer price;

    private Integer discount;

    private Integer beShareId;

    public OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodsSkuId(this.skuId);
        orderItem.setOrderId(this.orderId);
        orderItem.setName(this.name);
        orderItem.setQuantity(this.quantity);
        orderItem.setPrice(this.price);
        orderItem.setDiscount(this.discount);
        orderItem.setBeShareId(this.beShareId);
        return orderItem;
    }

    public OrderItemRetVo(OrderItem orderItem) {
        this.skuId = orderItem.getGoodsSkuId();
        this.orderId = orderItem.getOrderId();
        this.name = orderItem.getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.discount = orderItem.getDiscount();
        this.beShareId = orderItem.getBeShareId();
    }
}
