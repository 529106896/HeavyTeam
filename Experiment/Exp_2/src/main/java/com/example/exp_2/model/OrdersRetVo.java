package com.example.exp_2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单视图对象
 */

public class OrdersRetVo {
    private Integer id;

    private String orderSn;

    private Integer pid;

    private Integer orderType;

    private Integer state;

    private Integer subState;

    private String gmtCreate;

    private String gmtModified;

    private String confirmTime;

    private Integer originPrice;

    private Integer discountPrice;

    private Integer freightPrice;

    private Integer rebateNum;

    private String message;

    private Integer regionId;

    private String address;

    private String mobile;

    private String consignee;

    private Integer couponId;

    private Integer grouponId;

    private Integer presaleId;

    private String shipmentSn;

    private List<OrderItemRetVo> orderItemList;

    public OrdersRetVo(Orders orders) {
        this.id = orders.getId();
        this.orderSn = orders.getOrderSn();
        this.pid = orders.getPid();

        if (null != orders.getOrderType()) {
            this.orderType = orders.getOrderType().getCode();
        }

        if (null != orders.getState()) {
           this.state = orders.getState().getCode();
        }

        if (null != orders.getSubState()) {
            this.subState = orders.getSubState().getCode();
        }

        this.gmtCreate = orders.getGmtCreate().toString();
        this.gmtModified = orders.getGmtModified().toString();
        this.confirmTime = orders.getConfirmTime().toString();
        this.originPrice = orders.getOriginPrice();
        this.discountPrice = orders.getDiscountPrice();
        this.freightPrice = orders.getFreightPrice();
        this.rebateNum = orders.getRebateNum();
        this.message = orders.getMessage();
        this.regionId = orders.getRegionId();
        this.address = orders.getAddress();
        this.mobile = orders.getMobile();
        this.consignee = orders.getConsignee();
        this.couponId = orders.getCouponId();
        this.presaleId = orders.getPresaleId();
        this.shipmentSn = orders.getShipmentSn();

        if (null != orders.getOrderItemList()) {
            List<OrderItemRetVo> orderItemList = new ArrayList<>(orders.getOrderItemList().size());

            for (OrderItem orderItem : orders.getOrderItemList()) {
                OrderItemRetVo orderItemRetVo = new OrderItemRetVo(orderItem);
                orderItemList.add(orderItemRetVo);
            }
            this.orderItemList = orderItemList;
        }
    }

//    public Orders createOrders() {
//        Orders orders = new Orders();
//        orders.setId(this.id);
//        orders.setOrderSn(this.orderSn);
//        orders.setPid(this.pid);
//        orders.setOrderType(Orders.Type.NORMAL);
//        orders.setState(Orders.Status.CREATED);
//        orders.setSubState(Orders.Status.CREATED);
//    }

}