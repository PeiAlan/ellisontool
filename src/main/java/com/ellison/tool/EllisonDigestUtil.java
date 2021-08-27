package com.ellison.tool;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 * spring DigestUtils 加密工具类
 *
 * @author Ellison Pei
 * @date 2021-08-26 14:23
 * @version 1.0
 */
public class EllisonDigestUtil {

    /**
     * MD5 加盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String md5EncryptPassword(String salt, String password) {
        return new DigestUtils(MessageDigestAlgorithms.MD5).digestAsHex(String.format(salt, password));
    }

    /**
     * SHA_1 加盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String sha1EncryptPassword(String salt, String password) {
        return new DigestUtils(MessageDigestAlgorithms.SHA_1).digestAsHex(String.format(salt, password));
    }

    /**
     * SHA3-256 加盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String sha3256EncryptPassword(String salt, String password) {
        return new DigestUtils(MessageDigestAlgorithms.SHA3_256).digestAsHex(String.format(salt, password));
    }


}
