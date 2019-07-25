package me.itaot.payment.service.message;

import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/5 12:08
 * Email:itaot.me@gmail.com
 * Description:拆包服务
 */
public interface UniformParserService {
    /**
     * 拆包
     *
     * @param tranId  交易ID
     * @param message 报文
     * @return
     */
    public Map<String, String> unpack(String tranId, String message);
}
