package com.hope.cloud.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 14:12
 */
public class SecretUtil {

    private static final byte[] secret = new byte[]{-83, 75, 86, 34, -66, -33, 17, -41, 119, -121, -16, 75, 31, -112, -120, 105};

    public static String encrypt(String var) {
        return SecureUtil.aes(secret).encryptHex(var);
    }

    public static String decrypt(String var) {
        return SecureUtil.aes(secret).decryptStr(var, CharsetUtil.CHARSET_UTF_8);
    }

    public static boolean decryptEqual(String source, String target) {
        return decrypt(source).equals(target);
    }

    public static boolean decryptNotEqual(String source, String target) {
        return !decrypt(source).equals(target);
    }

    public static String generateToken() {
        String base = UUID.randomUUID().toString().replaceAll("-", "");
        return RandomUtil.randomString(base, 32);
    }

    private void demo() {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        String content = "test中文";
        // 构建
        AES aes = SecureUtil.aes(key);
        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
    }

}
