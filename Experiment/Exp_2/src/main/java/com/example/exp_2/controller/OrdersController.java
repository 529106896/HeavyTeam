package com.example.exp_2.controller;

import com.example.exp_2.model.OrdersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders", produces = "application/json;charset=UTF-8")
public class OrdersController {

    private final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @GetMapping("{id}")
    public Object getOrdersById(@PathVariable("id") Integer id) {
        return null;
    }

    @PostMapping("")
    public Object createOrders(@Validated @RequestBody OrdersVo ordersVo, BindingResult bindingResult) {

        return null;
    }

//    @PostMapping("")
//    public Object createOrders(@Validated @RequestBody)

}
