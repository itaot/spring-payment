package me.itaot.payment.service.pack.bean;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Author:itaot
 * CreateTime:2018/8/7 22:08
 * Email:itaot.me@gmail.com
 * Description:
 */
public class CompositeData {

    private static final Logger log = Logger.getLogger(CompositeData.class);
    private Element sysStruct = DocumentHelper.createElement("struct");
    private Element appStruct = DocumentHelper.createElement("struct");
    private Element localStruct = DocumentHelper.createElement("struct");
    private Element body = DocumentHelper.createElement("body");

    public CompositeData() {

    }

    public void addSysHead(Field field) {
        sysStruct.add(field.toElement());
    }

    public void addAppHead(Field field) {
        appStruct.add(field.toElement());
    }

    public void addLocalHead(Field field) {
        localStruct.add(field.toElement());
    }

    public void add(Field field) {
        body.add(field.toElement());
    }

    public Document asXML() {
        // 组织sys-header
        Element sysHead = DocumentHelper.createElement("sys-header");

        Element sysHeadDataElement = DocumentHelper.createElement("data");
        sysHeadDataElement.addAttribute("name", "SYS_HEAD");
        sysHeadDataElement.add(sysStruct);
        sysHead.add(sysHeadDataElement);
        log.debug("sysHead:" + sysHead.asXML());

        // 组织app-head
        Element appHead = DocumentHelper.createElement("app-header");

        Element appHeadDataElement = DocumentHelper.createElement("data");
        appHeadDataElement.addAttribute("name", "APP_HEAD");
        appHeadDataElement.add(appStruct);
        appHead.add(appHeadDataElement);
        log.debug("appHead:" + appHead.asXML());

        //组织local-header
        Element localHead = DocumentHelper.createElement("local-header");

        Element localHeadDataElement = DocumentHelper.createElement("data");
        localHeadDataElement.addAttribute("name", "LOCAL_HEAD");
        localHeadDataElement.add(localStruct);
        localHead.add(localHeadDataElement);
        log.debug("localHead:" + localHead.asXML());

        Document document = DocumentHelper.createDocument();
        Element serviceElement = DocumentHelper.createElement("service");
        serviceElement.add(sysHead);
        serviceElement.add(appHead);
        serviceElement.add(localHead);
        serviceElement.add(body);

        document.add(serviceElement);

        return document;
    }

}
