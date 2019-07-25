package me.itaot.payment.service.parser;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.common.exception.PackException;

import java.util.List;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/9 10:09
 * Email:itaot.me@gmail.com
 * Description:拆组包接口
 */
public interface ParserService {


    /**
     * 拆包
     *
     * @param mappings
     * @param message
     * @return
     * @throws PackException
     */
    public Map<String, String> parse(List<InstructMappingInf> mappings, String message) throws PackException;
}
