package com.ozbyte.epay.order.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

/**
 * 鉴权工具类
 *
 * @author alex
 * @version V1.0
 * @date 2022/7/27 15:06
 */
public class SignUtils {

    public static String sign(String signData, String privateKey) {
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA, privateKey, null);
        return sign.signHex(signData.getBytes());
    }

    /**
     * 生成签名字符串
     *
     * @param signData 需要签名数据
     * @return 生成后的签名字符串
     */
    public static String sign(String signData) {
        return sign(signData, ConfigSingleton.getInstance().getPrivateKey());
    }

    /**
     * sign 验证
     *
     * @param signData 需要验证的数据
     * @param signed   签名字符串
     * @return 验证结果
     */
    public static boolean verify(String signData, String signed) {
        return verify(signData, signed, ConfigSingleton.getInstance().getPublicKey());
    }

    public static boolean verify(String signData, String signed, String publicKey) {
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA, null, publicKey);
        return sign.verify(signData.getBytes(), HexUtil.decodeHex(signed));
    }

}
