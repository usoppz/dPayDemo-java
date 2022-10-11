package com.ozbyte.epay.order.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ozbyte.epay.order.req.PayOrderReq;
import com.ozbyte.epay.order.resp.SignOrderDTO;
import com.ozbyte.epay.order.utils.ConfigSingleton;
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
        String json = JSONUtil.parseObj(req).toStringPretty();
        System.out.println("请求参数：" + json);
        String result = HttpUtil.post(ConfigSingleton.getInstance().get("orderCreateUrl"), json);
        System.out.println("响应参数：" + JSONUtil.parseObj(result).toStringPretty());

        //响应数据有效验证
        verifyResp(result);
    }

    private static PayOrderReq getOrderReq() {
        PayOrderReq req = new PayOrderReq();
        req.setOrderNo("PO1234556778WD8GD0SD88879BBB");
        req.setAmount(1.00);
        req.setCurrency("usd");
        req.setSymbol("usdt");
        req.setNetwork("trc20");
        req.setCustomerNo("account no.");
        req.setProductName("product name");
        req.setReturnUrl("http://www.baidu.com");
        req.setNotifyUrl("http://127.0.0.1:8089/v1/order/notifyUrl");
        String signData = SignDataUtils.getOrderSignData(req);
        System.out.println("signData: " + signData);
        String signature = SignUtils.sign(signData);
        req.setSignature(signature);
        req.setPublicKey(ConfigSingleton.getInstance().getPublicKey());
        return req;
    }

    private static void verifyResp(String result) {
        JSONObject resp = JSON.parseObject(result);
        SignOrderDTO data = JSON.parseObject(String.valueOf(resp.getJSONObject("data")), SignOrderDTO.class);
        if (200 == resp.getInteger("code") && null != data) {
            String respSignData = SignDataUtils.getOrderRespSignData(data);
            if (SignUtils.verify(respSignData, data.getSignature(), data.getPublicKey())) {
                System.out.println("数据有效");
            }else {
                System.out.println("数据无效，请谨慎使用");
            }
        }
    }
}