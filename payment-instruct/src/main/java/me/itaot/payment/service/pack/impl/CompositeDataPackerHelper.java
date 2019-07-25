package me.itaot.payment.service.pack.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.service.pack.PackService;
import me.itaot.payment.service.pack.bean.CompositeData;
import me.itaot.payment.service.pack.bean.StringField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author:itaot
 * CreateTime:2018/8/7 22:08
 * Email:itaot.me@gmail.com
 * Description:神州信息CompositeData报文组装
 */
@Service
public class CompositeDataPackerHelper implements PackService {

    private static final Logger log = LoggerFactory.getLogger(CompositeDataPackerHelper.class);

    public String pack(List<InstructMappingInf> mappings, Map<String, String> data) {


        String message = "";

        CompositeData cd = new CompositeData();

        for (InstructMappingInf instructInf : mappings) {
            {
                String srcField = instructInf.getSrcField();
                String destPath = instructInf.getDestField();
                String value = data.get(srcField);
                if (null == value) {
                    value = "";
                }
                log.debug("srcField:" + srcField + ",destField:" + destPath);

                if (destPath.startsWith("SYS_HEAD.")) {
                    String[] path = destPath.split("\\.");
                    log.debug("path:" + Arrays.toString(path));
                    cd.addSysHead(new StringField(path[1], value));
                } else if (destPath.startsWith("LOCAL_HEAD.")) {
                    String[] path = destPath.split("\\.");
                    log.debug("path:" + Arrays.toString(path));
                    cd.addLocalHead(new StringField(path[1], value));
                } else if (destPath.startsWith("APP_HEAD.")) {
                    String[] path = destPath.split("\\.");
                    log.debug("path:" + Arrays.toString(path));
                    cd.addAppHead(new StringField(path[1], value));
                } else {
                    cd.add(new StringField(destPath, value));
                }

            }

        }

        message = cd.asXML().asXML();
        log.info("组包结果:{}", message);
        return message;
    }


}
