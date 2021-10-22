package com.example.exp_3.dao;
import com.example.exp_3.mapper.OrdersMapper;
import com.example.exp_3.model.OrderItem;
import com.example.exp_3.model.OrderItemPo;
import com.example.exp_3.model.Orders;
import com.example.exp_3.model.OrdersPo;
import com.example.exp_3.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.exp_3.util.Common.genSeqNum;

@Repository
public class OrdersDao {
    private Logger logger = LoggerFactory.getLogger(OrdersDao.class);

    @Autowired
    private OrdersMapper ordersMapper;

    public ReturnObject<List<Orders>> findOrders(OrdersPo ordersPo) {

        //long startTime = System.currentTimeMillis();
        List<OrdersPo> ordersPos = ordersMapper.findOrders(ordersPo);
        //long endTime = System.currentTimeMillis();
        //System.out.println("OrdersDao: findOrders: find order spend " + (endTime - startTime));

        List<Orders> retOrders = new ArrayList<>(ordersPos.size());

        for (OrdersPo o : ordersPos) {
            List<OrderItemPo> orderItemPos = o.getOrderItemPoList();
            Orders orders = new Orders(o);
            List<OrderItem> orderItemList = new ArrayList<>(orderItemPos.size());
            for (OrderItemPo orderItemPo : orderItemPos) {
                OrderItem orderItem = new OrderItem(orderItemPo);
                orderItemList.add(orderItem);
            }
            orders.setOrderItemList(orderItemList);
            retOrders.add(orders);
        }
        //long endTime = System.currentTimeMillis();
        //System.out.println("ordersDao : findOrders: left join total spend " + (endTime - startTime));
        return new ReturnObject<>(retOrders);
    }

    public ReturnObject<Orders> createOrders(Orders orders){
        OrdersPo ordersPo = orders.getOrdersPo();
        String seqNum = genSeqNum();
        ordersPo.setOrderSn(seqNum);

        int ret = ordersMapper.createOrders(ordersPo);
        if(orders.getOrderItemList()!=null){
            for (OrderItem orderItem : orders.getOrderItemList()){
                OrderItemPo orderItemPo = orderItem.getOrderItemPo();
                orderItemPo.setOrderId(orders.getId());
                orderItemPo.setPrice(5);
                ret = ordersMapper.createOrderItem(orderItemPo);
            }
        }
        ReturnObject<Orders> returnObject = new ReturnObject<>(orders);
        return returnObject;
    }

    public ReturnObject<List<Orders>> findOrdersWithItems(OrdersPo ordersPo) {
        List<OrdersPo> ordersPos = ordersMapper.findOrdersWithoutItems(ordersPo);

        List<Orders> retOrders = new ArrayList<>(ordersPos.size());

        for (OrdersPo o : ordersPos) {

            List<OrderItemPo> orderItemPos = ordersMapper.findOrderItems(o.getId());

            Orders orders = new Orders(o);

            List<OrderItem> orderItemList = new ArrayList<>(orderItemPos.size());
            for (OrderItemPo orderItemPo : orderItemPos) {
                OrderItem orderItem = new OrderItem(orderItemPo);
                orderItemList.add(orderItem);
            }
            orders.setOrderItemList(orderItemList);
            //System.out.println(orders.getOrderItemList());
            retOrders.add(orders);
        }
        return new ReturnObject<>(retOrders);
    }

}