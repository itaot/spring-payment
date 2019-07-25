package me.itaot.payment.service.db;

import me.itaot.payment.common.beans.InstructMappingInf;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/8/5 16:15
 * Email:itaot.me@gmail.com
 * Description:
 */
public interface InstructMappingInfoService {
    public List<InstructMappingInf> getInstructMappingInf(String sysCode, String subSysCode, String tranId);

    public List<InstructMappingInf> getInstructMappingInf(String tranId);
}