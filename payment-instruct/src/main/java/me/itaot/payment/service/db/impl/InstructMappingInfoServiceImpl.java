package me.itaot.payment.service.db.impl;

import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.dao.InstructMappingInfDao;
import me.itaot.payment.service.db.InstructMappingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/8/5 16:29
 * Email:itaot.me@gmail.com
 * Description:
 */
@Service
public class InstructMappingInfoServiceImpl implements InstructMappingInfoService {

    private static final Logger log = LoggerFactory.getLogger(InstructMappingInfoServiceImpl.class);

    @Autowired
    private InstructMappingInfDao instructMappingInfDao;

    @Override
    public List<InstructMappingInf> getInstructMappingInf(String sysCode, String subSysCode, String tranId) {

        List<InstructMappingInf> instructMappingInfs = instructMappingInfDao.getInstructMapping(sysCode, subSysCode, tranId);

        if (tranId.startsWith("I")) {
            for (int i = 0; i < instructMappingInfs.size(); i++) {
                InstructMappingInf mappingInf = instructMappingInfs.get(i);
                String srcField = mappingInf.getSrcField();
                srcField = srcField.replace(".", "/");
                srcField = "/" + srcField;
                mappingInf.setSrcField(srcField);
                instructMappingInfs.set(i, mappingInf);
            }
        }

        return instructMappingInfs;
    }

    @Override
    public List<InstructMappingInf> getInstructMappingInf(String tranId) {
        String[] tranInf = tranId.split("-");
        String sysCode = tranInf[0];
        String subSysCode = tranInf[1];
        String msgCode = tranInf[2];
        log.debug("SysCode:{} SubSysCode:{} MsgCode:{}", tranInf);
        return getInstructMappingInf(sysCode, subSysCode, msgCode);
    }
}