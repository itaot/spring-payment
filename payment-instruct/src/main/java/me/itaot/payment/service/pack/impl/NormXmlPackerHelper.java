package me.itaot.payment.service.pack.impl;

import com.alibaba.dubbo.config.annotation.Service;
import me.itaot.payment.common.beans.InstructMappingInf;
import me.itaot.payment.common.exception.PackException;
import me.itaot.payment.service.pack.PackService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Author:itaot
 * CreateTime:2018/7/16 20:05
 * Email:itaot.me@gmail.com
 * Description:普通Xml组包
 */
@Service
public class NormXmlPackerHelper implements PackService {

    private static final Logger log = LoggerFactory.getLogger(NormXmlPackerHelper.class);

    private static final Pattern PATTERN_METHOD = Pattern
            .compile("^([a-zA-Z]+[0-9a-zA-Z_]*[.]{1}[a-zA-Z]+[0-9a-zA-Z_]*)+\\({1}.*\\){1}$");
    private static final Pattern PATTERN_VALUE = Pattern.compile("\".*\"");
    private static final Pattern PATTERN_KEY = Pattern.compile("^.*$");

    private static final String OTHERS_XMLNS = "xmlns";

    @Override
    public String pack(List<InstructMappingInf> mappings, Map<String, String> data) throws PackException {

        log.info("Start Norm Xml Pack...");
        long startTime = System.currentTimeMillis();
        String xmlns = "";

        Document doc = DocumentHelper.createDocument();
        Element rootElement = null;
        Element lastElement = null;
        Element parentElement = null;

        for (InstructMappingInf instructInf : mappings) {
            log.debug("instructInf:{}", instructInf);
            String npc = instructInf.getDestField();
            String sys = instructInf.getSrcField();

            log.debug("{}=>{}", sys, npc);

            String value = "";

            try {
                value = getValue(data, sys);
                if (value.isEmpty()) {
                    value = instructInf.getDefaultValue();
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e1) {
                e1.printStackTrace();
                throw new PackException("400001", "组包取值异常:" + e1.getLocalizedMessage());
            }

            //log.debug("组[{},{}]到节点{}中", sys, value, npc);
            String[] nodes = npc.split("\\.");
            for (int j = 0; j < nodes.length; j++) {
                boolean isAttribute = false;
                boolean existElement = false;
                if (nodes[j].startsWith("@")) {
                    isAttribute = true;
                }

                if (rootElement == null) {
                    if (xmlns != null && !xmlns.isEmpty()) {
                        lastElement = doc.addElement(nodes[j], xmlns);
                    } else {
                        lastElement = doc.addElement(nodes[j]);
                    }
                    rootElement = lastElement;
                    continue;
                } else { // 第二条配置处理
                    //根节点，已经组过，无需再组
                    if (j == 0) {
                        continue;
                    }

                    if (lastElement == rootElement) {
                        parentElement = rootElement;
                    } else {
                        parentElement = lastElement;
                    }

                    log.debug("父节点:{}\t子节点:{}", parentElement.getName(), nodes[j]);

                    for (Object obj : parentElement.elements()) {
                        String eName = ((Element) obj).getName();
                        if (eName.equals(nodes[j])) {
                            existElement = true;
                            lastElement = (Element) obj;
                        }
                    }

                }

                if (j == nodes.length - 1) {

                    log.debug("开始处理:{}-->{}", sys, npc);


                    if (isAttribute) {
                        lastElement.addAttribute(nodes[j].substring(1, nodes[j].length()), value);
                    } else if (!existElement) {
                        lastElement = lastElement.addElement(nodes[j]);
                        lastElement.setText(value);
                    }

                    lastElement = rootElement;
                } else {
                    if (!existElement) {
                        lastElement = lastElement.addElement(nodes[j]);
                    }
                }

            }

            log.debug("tempXml:{}", doc.asXML());
            log.debug("\n");

        }

        log.debug("Pack Result:{}", doc.asXML());
        long endTime = System.currentTimeMillis();

        log.info("End Norm Xml Pack,耗时{}ms", (endTime - startTime));

        return doc.asXML();

    }


    /**
     * @param msg
     * @param field
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static String getValue(Map<String, String> msg, String field)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // me.itaot.springpayment.AmtUtil.toChar(TOATAL_AMT)
        // TOTAL_AMT
        // "TOTAL_AMT"

        String text = "";

        if (PATTERN_METHOD.matcher(field).matches()) {
            log.debug("配置的为Java方法[" + field + "]");

            String[] texts = field.split("\\(");
            String codeInfo = texts[0];
            String[] parameters = texts[1].split("\\)")[0].split(",");

            int methodIndex = codeInfo.lastIndexOf(".");
            String className = codeInfo.substring(0, methodIndex);
            String methodName = codeInfo.substring(methodIndex + 1);

            log.debug("反射调用类为:" + className);
            log.debug("反射调用方法为:" + methodName);
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, String.class);

            String[] invokeParas = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                invokeParas[i] = msg.get(parameters[i]);
            }

            log.debug("反射方法调用参数:" + Arrays.toString(invokeParas));

            text = (String) method.invoke(clazz, invokeParas);

        } else if (PATTERN_VALUE.matcher(field).matches()) {
            log.debug("配置的为固定值[" + field + "]");
            text = field.replaceAll("\"", "");
        } else if (PATTERN_KEY.matcher(field).matches()) {
            log.debug("配置的为属性[" + field + "]");
            text = msg.get(field);
        } else {
            log.error("非法参数类型");
        }

        log.debug("text===>" + text);
        return (null == text) ? "" : text.trim();
    }

}
