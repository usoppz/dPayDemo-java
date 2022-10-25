package com.ozbyte.epay.order.utils;

import cn.hutool.setting.Setting;

/**
 * 订单队列单例模式
 *
 * @author alex
 * @version V1.0
 * @date 2022/7/28 14:34
 */
public class ConfigSingleton extends Setting {

    private static final ConfigSingleton CONFIG = new ConfigSingleton("config.properties");

    private ConfigSingleton(String s) {
        super(s);
    }

    public static ConfigSingleton getInstance() {
        return CONFIG;
    }

    /**
     * 获取官方平台公钥
     * @return 平台公钥
     */
    public String getPublicKey() {
        return get("dPayRsaPublicKey");
    }

    /**
     * 获取商户私钥
     * @return 商户私钥
     */
    public String getPrivateKey() {
        return get("rsaPrivateKey");
    }

    public String getAppId() {
        return get("appId");
    }


}
