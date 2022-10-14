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

    public String getPublicKey() {
        return get("dPayRsaPublicKey");
    }

    public String getPrivateKey() {
        return get("rsaPrivateKey");
    }

    public String getAppId() {
        return get("appId");
    }


}
