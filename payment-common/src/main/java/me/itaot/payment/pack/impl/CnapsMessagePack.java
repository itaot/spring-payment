package me.itaot.payment.pack.impl;

import me.itaot.payment.pack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 人行二代支付报文组包实现
 */
public class CnapsMessagePack implements MessagePack {

    private static final Logger log = LoggerFactory.getLogger(CnapsMessagePack.class);

    public String pack(Map<String, String> msg) {
        log.info("Start execute cnaps instruct pack ... ");
        String instruct = "";
        String tranName = msg.get("$TRAN_NAME");

        log.info("Start execute cnaps instruct pack ... ");
        return instruct;
    }
}