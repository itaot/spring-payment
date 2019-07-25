package me.itaot.payment.provider.unpack;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.service.db.InstructMappingInfoService;
import me.itaot.payment.service.message.UniformParserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/9 10:07
 * Email:itaot.me@gmail.com
 * Description:
 */
public class UniformParserHelper implements UniformParserService {


    @Autowired
    private InstructMappingInfoService instructMappingInfoService;

    @Override
    public Map<String, String> unpack(String tranId, String message) {
        List<InstructMappingInf> cpcnInstructMappingInfos = instructMappingInfoService.getInstructMappingInf(tranId);
        return null;
    }
}
