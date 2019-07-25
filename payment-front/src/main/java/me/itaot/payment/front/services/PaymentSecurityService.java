package me.itaot.payment.front.services;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.front.beans.FrontException;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/8 12:47
 * Email:itaot.me@gmail.com
 * Description:签名验签接口
 */
public interface PaymentSecurityService {

    public Map<String, String> parsePlaintext(String plainText) throws FrontException;

    public boolean rawVerify(Map<String, String> kv);

}