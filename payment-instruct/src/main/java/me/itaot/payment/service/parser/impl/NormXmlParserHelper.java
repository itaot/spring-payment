package me.itaot.payment.service.parser.impl;

import com.alibaba.dubbo.config.annotation.Service;
import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.common.exception.PackException;
import me.itaot.payment.service.InstructMappingInfoService;
import me.itaot.payment.service.parser.ParserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/8/5 15:28
 * Email:itaot.me@gmail.com
 * Description:普通XML拆包实现
 */
@Service
public class NormXmlParserHelper implements ParserService {

    private static final Logger log = LoggerFactory.getLogger(NormXmlParserHelper.class);

    @Override
    public Map<String, String> parse(List<InstructMappingInf> cpcnInstructMappingInfos, String message) {

        Map<String, String> unpackResult = new HashMap<String, String>();
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new ByteArrayInputStream(message.getBytes()));
        } catch (DocumentException e) {
            log.error("Xml读取异常{}", e);
        }

        for (InstructMappingInf instructMappingInf : cpcnInstructMappingInfos) {
            String srcField = instructMappingInf.getSrcField();
            String destField = instructMappingInf.getDestField();
            log.debug("XPath:{},DestField:{}", srcField, destField);
            List<Element> elements = doc.selectNodes(srcField);
            if (elements.size() == 1) {
                Element element = elements.get(0);
                String value = element.getText();
                unpackResult.put(destField, value);
            }
        }

        return unpackResult;
    }
}
