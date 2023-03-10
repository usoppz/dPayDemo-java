package com.ozbyte.epay.order.controller;

import com.alibaba.fastjson.JSON;
import com.ozbyte.dpay.req.OrderNotifyReq;
import com.ozbyte.dpay.utils.SignDataUtils;
import com.ozbyte.dpay.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

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
        if (SignUtils.verify(respSignData, req.getSignature())) {
            System.out.println("数据有效");
        }else {
            System.out.println("数据无效，请谨慎使用");
        }
        return "success";
    }

    @GetMapping("/returnUrl")
    public String redirect(@RequestParam(value = "orderNo") String orderNo) {
        System.out.println("pay successful : "+orderNo);
        return "success";
    }

}
