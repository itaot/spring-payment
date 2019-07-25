package me.itaot.payment.cpcn.service;

import me.itaot.payment.common.beans.InstructMappingInf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:itaot
 * CreateTime:2018/7/21 8:38
 * Email:itaot.me@gmail.com
 * Description:
 */
public interface CpcnInstructMappingService {

    public static Map<String, List<InstructMappingInf>> MAPPING_CACHE = new ConcurrentHashMap<String, List<InstructMappingInf>>();

    public List<InstructMappingInf> getMapping(String sysCode, String subSysCode, String tranId);
}
