package com.ozbyte.epay.order.controller;

import com.alibaba.fastjson.JSON;
import com.ozbyte.epay.order.req.OrderNotifyReq;
import com.ozbyte.epay.order.utils.SignDataUtils;
import com.ozbyte.epay.order.utils.SignUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知回调接收接口
 *
 * @author alex
 * @version V1.0
 * @date 2022/9/19 11:30
 */
@RestController
@RequestMapping("v1/order")
public class NotifyReceiveController {

    @PostMapping("/notifyUrl")
    public String confirmCredited(@RequestBody OrderNotifyReq req) {
        System.out.println(JSON.toJSONString(req));
        //数据有效性验证
        String respSignData = SignDataUtils.getOrderNotifySignData(req);
        if (SignUtils.verify(respSignData, req.getSignature(), req.getPublicKey())) {
            System.out.println("数据有效");
        }else {
            System.out.println("数据无效，请谨慎使用");
        }
        return "success";
    }

}
