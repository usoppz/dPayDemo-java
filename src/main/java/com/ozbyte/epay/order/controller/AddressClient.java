package com.ozbyte.epay.order.controller;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.TypeReference;
import com.ozbyte.epay.order.req.AddressAddApiReq;
import com.ozbyte.epay.order.req.AddressDisabledApiReq;
import com.ozbyte.epay.order.req.AddressGenerateReq;
import com.ozbyte.epay.order.req.AddressListApiReq;
import com.ozbyte.epay.order.resp.AddressCofigDTO;
import com.ozbyte.epay.order.resp.AddressDTO;
import com.ozbyte.epay.order.resp.AddressGenerateDTO;
import com.ozbyte.epay.order.resp.Result;
import com.ozbyte.epay.order.utils.ConfigSingleton;
import com.ozbyte.epay.order.utils.InterfaceUtil;
import com.ozbyte.epay.order.utils.SignUtils;

import java.util.List;

/**
 * 钱包地址客户端调用
 *
 * @author alex
 * @version V1.0
 * @date 2022/10/20 11:39
 */
public class AddressClient {

    public static void main(String[] args) {
        //1. generate address
//        generateAddress(ConfigSingleton.getInstance().get("addressCreateUrl"));
        //2. generate and config address
//        generateAddress(ConfigSingleton.getInstance().get("addressGenerateAndConfigUrl"));
        //3.disable or enable address
//        disableAddress(ConfigSingleton.getInstance().get("addressDisabledUrl"));
        //4.config address
//        configAddress(ConfigSingleton.getInstance().get("addressConfigUrl"));
        //5.list address
        listAddress(ConfigSingleton.getInstance().get("addressListUrl"));

    }

    private static void generateAddress(String url) {
        AddressGenerateReq req = getAddressGenerateReq();
        Result<AddressGenerateDTO> resp = InterfaceUtil.invoke(req, url, new TypeReference<AddressGenerateDTO>() {
        });
        if (null == resp.getData()) {
            System.out.println(" result's data is null. code:" + resp.getCode() + " message:" + resp.getMsg());
            return;
        }
        String respSignData = req.getAppId() + resp.getData().getAddress() + resp.getData().getPrivateKey();
        System.out.println("The current respOrderSignData : " + respSignData);
        InterfaceUtil.verifyResp(respSignData, resp.getData().getSignature());

    }

    private static AddressGenerateReq getAddressGenerateReq() {
        AddressGenerateReq req = new AddressGenerateReq();
        req.setAppId(ConfigSingleton.getInstance().getAppId());
        req.setNetwork("trc20");
        String signData = req.getAppId() + req.getNetwork();
        String signature = SignUtils.sign(signData);
        req.setSignature(signature);
        System.out.println("signature: " + signature);
        return req;
    }

    private static void disableAddress(String url) {
        AddressDisabledApiReq req = new AddressDisabledApiReq();
        req.setAppId(ConfigSingleton.getInstance().getAppId());
        req.setAddress("TAfJbSj3BWv1p4F4ykAFCYtrY6ZiycEruA");
        req.setStatus((byte) 1);
        String signData = req.getAppId() + req.getAddress() + req.getStatus();
        req.setSignature(SignUtils.sign(signData));
        Result<Object> resp = InterfaceUtil.invoke(req, url, null);
        System.out.println(resp.getMsg());

    }

    private static void configAddress(String url) {
        AddressAddApiReq req = new AddressAddApiReq();
        req.setAppId(ConfigSingleton.getInstance().getAppId());
        req.setNetwork("trc20");
        req.setAddress("TCpznirJYaqcRDs3qsESAU9YWt31Sv4CLB");
        String signData = req.getAppId() + req.getAddress() + req.getNetwork();
        String signature = SignUtils.sign(signData);
        req.setSignature(signature);
        Result<AddressCofigDTO> resp = InterfaceUtil.invoke(req, url, new TypeReference<AddressCofigDTO>() {
        });
        if (null == resp.getData()) {
            System.out.println(" result's data is null. code:" + resp.getCode() + " message:" + resp.getMsg());
            return;
        }
        String respSignData = req.getAppId() + resp.getData().getAddress();
        System.out.println("The current respOrderSignData : " + respSignData);
        InterfaceUtil.verifyResp(respSignData, resp.getData().getSignature());
    }

    private static void listAddress(String url) {
        AddressListApiReq req = new AddressListApiReq();
        req.setAppId(ConfigSingleton.getInstance().getAppId());
        req.setSignature(SignUtils.sign(req.getAppId()));
        Result<List<AddressDTO>> resp = InterfaceUtil.invoke(req, url, new TypeReference<List<AddressDTO>>() {
        });
        if (null == resp.getData()) {
            System.out.println(" result's data is null. code:" + resp.getCode() + " message:" + resp.getMsg());
        }
    }
}
