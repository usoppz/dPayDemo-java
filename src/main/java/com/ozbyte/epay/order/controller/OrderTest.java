package com.ozbyte.epay.order.controller;

import com.alibaba.fastjson.JSON;
import com.ozbyte.dpay.client.OrderClient;
import com.ozbyte.dpay.req.PayOrderReq;
import com.ozbyte.dpay.resp.SignOrderDTO;

/**
 * 支付订单创建演示类
 *
 * @author alex
 * @version V1.0
 * @date 2023/3/9 15:26
 */
public class OrderTest {

    public static void main(String[] args) {
        SignOrderDTO order = OrderClient.createOrder(getOrderReq());
        System.out.println(JSON.toJSONString(order));
    }

    /**
     * 创建订单请求参数
     */
    private static PayOrderReq getOrderReq() {
        PayOrderReq req = new PayOrderReq();
        req.setOrderNo("PO123455678WD8GD0SD9999979BEF");
        req.setAmount(100.00);
        req.setCurrency("cny");
        req.setSymbol("usdt");
        req.setOrderType(2);
        req.setNetwork("trc20");
        req.setCustomerNo("test000301");
        req.setProductName("product0001");
        req.setReturnUrl("http://127.0.0.1:8089/v1/order/returnUrl");
        req.setNotifyUrl("http://127.0.0.1:8089/v1/order/notifyUrl");
        return req;
    }

}
