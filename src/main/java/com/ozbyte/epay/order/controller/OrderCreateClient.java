package com.ozbyte.epay.order.controller;

import com.alibaba.fastjson.TypeReference;
import com.ozbyte.epay.order.req.PayOrderReq;
import com.ozbyte.epay.order.resp.Result;
import com.ozbyte.epay.order.resp.SignOrderDTO;
import com.ozbyte.epay.order.utils.ConfigSingleton;
import com.ozbyte.epay.order.utils.InterfaceUtil;
import com.ozbyte.epay.order.utils.SignDataUtils;
import com.ozbyte.epay.order.utils.SignUtils;

/**
 * 订单业务客户端
 *
 * @author alex
 * @version V1.0
 * @date 2022/9/8 11:37
 */
public class OrderCreateClient {

    public static void main(String[] args) {

        PayOrderReq req = getOrderReq();
        Result<SignOrderDTO> resp = InterfaceUtil.invoke(req,ConfigSingleton.getInstance().get("orderCreateUrl"), new TypeReference<SignOrderDTO>() {});
        if(null == resp.getData()){
            System.out.println(" result's data is null. code:"+ resp.getCode()+ " message:"+ resp.getMsg());
            return;
        }
        String respSignData = SignDataUtils.getOrderRespSignData(resp.getData());
        System.out.println("The current respOrderSignData : " + respSignData);
        //响应数据有效验证
        InterfaceUtil.verifyResp(respSignData, resp.getData().getSignature());
    }

    private static PayOrderReq getOrderReq() {
        PayOrderReq req = new PayOrderReq();
        req.setAppId(ConfigSingleton.getInstance().getAppId());
        req.setOrderNo("PO1234556778WD8GD0SD99979BEF");
        req.setAmount(50.00);
        req.setCurrency("cny");
        req.setSymbol("usdt");
        req.setNetwork("trc20");
        req.setCustomerNo("test00001");
        req.setProductName("product test name");
        req.setReturnUrl("http://127.0.0.1:8089/v1/order/returnUrl");
        req.setNotifyUrl("http://127.0.0.1:8089/v1/order/notifyUrl");
        String signData = SignDataUtils.getOrderSignData(req);
        System.out.println("signData: " + signData);
        String signature = SignUtils.sign(signData);
        req.setSignature(signature);
        return req;
    }

}
