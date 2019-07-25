package me.itaot.payment.cpcn.service;

import me.itaot.payment.beans.PaymentException;

/**
 * Author:itaot
 * CreateTime:2018/7/13 10:37
 * Email:itaot.me@gmail.com
 * Description:签名验签服务
 */
public interface CpcnSecurityService {

    /**
     * 验签
     *
     * @param responseMsg 报文
     * @param signStr     签名串
     * @return 验签结果
     */
    public boolean verify(String responseMsg, String signStr) throws PaymentException;

}
