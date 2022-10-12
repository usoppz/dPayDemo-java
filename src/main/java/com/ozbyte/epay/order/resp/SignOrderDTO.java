package com.ozbyte.epay.order.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于鉴权的订单数据
 *
 * @author alex
 * @version V1.0
 * @date 2022/7/27 16:32
 */
@Data
public class SignOrderDTO {

    /**
     * 订单官方编号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 订单支付金额
     */
    private Double tradeAmt;

    /**
     * 订单币种单位。支持：CNY、USD。
     */
    private String currency;

    /**
     * 订单支付币种。目前固定为 USDT
     */
    private String symbol;

    /**
     * 订单有效时间
     */
    private Date usefulTime;

    /**
     * 订单收款地址
     */
    private String address;

    /**
     * 订单收款码，图片base64字符串格式
     */
    private String qrcodeUrl;

    /**
     * 官方收银台地址
     */
    private String cashierUrl;

    /**
     * 官方公钥 用与签名验证
     */
    private String publicKey;

    /**
     * 签名串
     */
    private String signature;

}
