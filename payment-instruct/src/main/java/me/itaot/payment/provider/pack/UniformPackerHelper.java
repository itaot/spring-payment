package me.itaot.payment.provider.pack;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.common.exception.PackException;
import me.itaot.payment.service.db.InstructMappingInfoService;
import me.itaot.payment.service.message.UniformPackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/9 9:58
 * Email:itaot.me@gmail.com
 * Description:
 */
public class UniformPackerHelper implements UniformPackService {

    @Autowired
    private InstructMappingInfoService instructMappingInfoService;

    @Override
    public String pack(String tranId, Map<String, String> data) throws PackException {
        List<InstructMappingInf> mappings = instructMappingInfoService.getInstructMappingInf(tranId);
        return null;
    }
}
