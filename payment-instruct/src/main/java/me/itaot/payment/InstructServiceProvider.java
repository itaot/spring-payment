package me.itaot.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Author:itaot
 * CreateTime:2018/8/5 12:22
 * Email:itaot.me@gmail.com
 * Description:
 */
@Configuration
@ComponentScan
public class InstructServiceProvider {

    private static final Logger log = LoggerFactory.getLogger(InstructServiceProvider.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application.xml", "provider.xml"});

        context.start();
        log.info("Instruct Application Start ... ");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}