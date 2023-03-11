package com.ozbyte.epay.order.controller;

import com.alibaba.fastjson.JSON;
import com.ozbyte.dpay.client.AddressClient;
import com.ozbyte.dpay.req.AddressAddApiReq;
import com.ozbyte.dpay.req.AddressDisabledApiReq;
import com.ozbyte.dpay.req.AddressGenerateReq;
import com.ozbyte.dpay.resp.AddressCofigDTO;
import com.ozbyte.dpay.resp.AddressDTO;
import com.ozbyte.dpay.resp.AddressGenerateDTO;

import java.util.List;

/**
 * 无需归集订单地址相关操作演示
 *
 * @author alex
 * @version V1.0
 * @date 2023/3/9 15:07
 */
public class AddressTest {

    public static void main(String[] args) {
//        generateAddress();
//        addressGenerateAndConfig();
//        disableAddress();
//        configAddress();
        listAddress();
    }

    /**
     * 订单地址生成
     */
    private static void generateAddress() {
        AddressGenerateReq req = new AddressGenerateReq();
        req.setNetwork("trc20");
        AddressGenerateDTO address = AddressClient.generateAddress(req);
        System.out.println(JSON.toJSONString(address));
    }

    /**
     * 订单地址生成并且配置 （无需归集订单类型使用此接口）
     */
    private static void addressGenerateAndConfig() {
        AddressGenerateReq req = new AddressGenerateReq();
        req.setNetwork("trc20");
        AddressGenerateDTO address = AddressClient.addressGenerateAndConfig(req);
        System.out.println(JSON.toJSONString(address));
    }

    /**
     * 订单收款地址禁用（无需归集订单类型使用此接口）
     */
    private static void disableAddress() {
        AddressDisabledApiReq req = new AddressDisabledApiReq();
        req.setAddress("TXLu3EyYZC3tfMWGZmt58iRvmVwuer2Bix");
        req.setStatus((byte) 2);
        String msg = AddressClient.disableAddress(req);
        System.out.println(msg);
    }

    /**
     * 指定收款地址配置 （无需归集订单类型使用此接口）
     */
    private static void configAddress() {
        AddressAddApiReq req = new AddressAddApiReq();
        req.setNetwork("trc20");
        req.setAddress("TW4FVhVqxeCUUZXM2aWKK62YJGNpqrfgGE");
        AddressCofigDTO addressCofigDTO = AddressClient.addAddress(req);
        System.out.println(JSON.toJSONString(addressCofigDTO));
    }

    /**
     * 无需归集订单的收款地址查询
     */
    private static void listAddress() {
        List<AddressDTO> addresses = AddressClient.listAddress();
        System.out.println(JSON.toJSONString(addresses));
    }

}
