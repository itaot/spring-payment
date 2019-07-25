package me.itaot.payment.cpcn.service.impl;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.cpcn.jpa.CpcnInstructInfJpa;
import me.itaot.payment.cpcn.service.CpcnInstructMappingService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Author:itaot
 * CreateTime:2018/7/21 8:39
 * Email:itaot.me@gmail.com
 * Description:
 */
@Service
public class CpcnInstructMappingServiceImpl implements CpcnInstructMappingService {


    @Override
    public List<InstructMappingInf> getMapping(String sysCode, String subSysCode, String tranId) {
//        List<InstructMappingInf> tranInstructMapping = MAPPING_CACHE.get(tranId);
//
//        if (tranInstructMapping == null) {
//            tranInstructMapping = cpcnInstructInfJpa.getMapping(sysCode, subSysCode, tranId);
//
//            if (tranId.startsWith("I")) {
//                for (InstructMappingInf e : tranInstructMapping) {
//                    String srcField = e.getSrcField();
//                    srcField = srcField.replace(".", "/");
//                    srcField = "/" + srcField;
//                    e.setSrcField(srcField);
//                }
//            }
//
//            MAPPING_CACHE.put(sysCode + "-" + subSysCode + "-" + tranId, tranInstructMapping);
//        }
//
        return null;
    }
}
