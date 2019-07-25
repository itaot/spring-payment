package me.itaot.payment.service.message;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.common.exception.PackException;

import java.util.List;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/5 12:02
 * Email:itaot.me@gmail.com
 * Description:拆组包接口
 */
public interface UniformPackService {
    /**
     * 组包
     *
     * @param data 数据域
     * @return
     * @throws PackException
     */
    public String pack(String tranId, Map<String, String> data) throws PackException;

}
