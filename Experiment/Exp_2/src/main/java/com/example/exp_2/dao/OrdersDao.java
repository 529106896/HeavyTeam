package com.example.exp_2.dao;
import com.example.exp_2.mapper.OrdersMapper;
import com.example.exp_2.model.OrderItem;
import com.example.exp_2.model.OrderItemPo;
import com.example.exp_2.model.Orders;
import com.example.exp_2.model.OrdersPo;
import com.example.exp_2.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.exp_2.util.Common.genSeqNum;

@Repository
public class OrdersDao {
    private Logger logger = LoggerFactory.getLogger(OrdersDao.class);

    @Autowired
    private OrdersMapper ordersMapper;

    public ReturnObject<List<Orders>> findOrders(OrdersPo ordersPo){
        logger.info("findOrders: ordersPo ="+ordersPo);
        List<OrdersPo> ordersPos=ordersMapper.findOrders(ordersPo);
        logger.info("findOrders: ordersPos ="+ordersPos);

        List<Orders> retOrders=new ArrayList<>(ordersPos.size());

        for (OrdersPo orderPo : ordersPos){
            Orders item = new Orders(orderPo);
            List<OrderItem> orderItemList=new ArrayList<>(orderPo.getOrderItemPoList().size());

            for (OrderItemPo orderItemPo:orderPo.getOrderItemPoList()){
                OrderItem orderItem = new OrderItem(orderItemPo);
                orderItemList.add(orderItem);
            }

            item.setOrderItemList(orderItemList);
            retOrders.add(item);
        }
        logger.info("findOrders: retOrders = "+retOrders);
        return new ReturnObject<>(retOrders);
    }

    public ReturnObject<Orders> createOrders(Orders orders){
        OrdersPo ordersPo = orders.getOrdersPo();
        String seqNum = genSeqNum();
        ordersPo.setOrderSn("G"+seqNum);

        int ret = ordersMapper.createOrders(ordersPo);
        if(orders.getOrderItemList()!=null){
            for (OrderItem orderItem : orders.getOrderItemList()){
                OrderItemPo orderItemPo = orderItem.getOrderItemPo();
                orderItemPo.setOrderId(orders.getId());
                ret = ordersMapper.createOrderItem(orderItemPo);
            }
        }
        ReturnObject<Orders> returnObject = new ReturnObject<>(orders);
        return returnObject;
    }
}