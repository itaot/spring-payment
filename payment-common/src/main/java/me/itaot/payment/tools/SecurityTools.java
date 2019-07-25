package me.itaot.payment.tools;

import java.io.UnsupportedEncodingException;

/**
 * Author:itaot
 * CreateTime:2018/7/8 16:11
 * Email:itaot.me@gmail.com
 * Description:安全相关工具类
 */
public class SecurityTools {


    /**
     * BASE64加密
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String base64encode(String plainText) throws UnsupportedEncodingException {
        //byte[] bytes = Base64.encodeBase64(plainText.getBytes());
        //String cipherText = new String(bytes, "UTF-8");
        return "";
    }

    /**
     * Base64解密
     *
     * @param cipherText 密文
     * @return 明文
     */
    public static String base64decode(String cipherText) throws UnsupportedEncodingException {
        //byte[] bytes = Base64.decodeBase64(cipherText);
        //String plainText = new String(bytes, "UTF-8");
        return "";
    }

}
