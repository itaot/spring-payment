package me.itaot.payment.cpcn.service;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.common.exception.PackException;
import org.dom4j.DocumentException;

import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/13 10:29
 * Email:itaot.me@gmail.com
 * Description:中金支付报文处理
 */
public interface CpcnMesssageService {

    /**
     * 解析明文报文
     *
     * @param cipherText 密文
     * @return 明文
     * @throws PaymentException 验签或其他异常时
     */
    public String parsePlainText(String cipherText) throws PaymentException;

    /**
     * 组中金报文
     *
     * @param tranId
     * @param data
     * @return
     * @throws PackException
     */
    public String pack(String sysCode, String subSysCode, String tranId, Map<String, String> data) throws PackException;


    /**
     * 组织中金请求密文
     *
     * @param plainText
     * @return
     */
    public Map<String, String> orgRequestParas(String plainText);

    /**
     * 拆包
     *
     * @param sysCode
     * @param subSysCode
     * @param tranId
     * @param message
     * @return
     */
    public Map<String, String> unpack(String sysCode, String subSysCode, String tranId, String message);
}