package me.itaot.payment.front.filter;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.beans.PaymentResponse;
import me.itaot.payment.front.beans.FrontException;
import me.itaot.payment.front.services.PaymentSecurityService;
import me.itaot.payment.tools.SecurityTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/8 14:32
 * Email:itaot.me@gmail.com
 * Description:
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "securityFilter")
public class SecurityFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private PaymentSecurityService paymentSecurityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Start execute SecurityFilter ... ");

        try {
            //1、base64解码
            String cipherText = request.getAttribute("message").toString();
            if (cipherText.isEmpty()) {
                throw new FrontException("F00003");
            }
            String plainText = SecurityTools.base64decode(cipherText);

            //2、解析请求报文为键值对
            Map<String, String> paras = paymentSecurityService.parsePlaintext(plainText);

            //3、验签
            boolean isVerify = paymentSecurityService.rawVerify(paras);

            if (!isVerify) {
                log.error("验签失败!");
                throw new FrontException("F00002");
            }

            chain.doFilter(request, response);
        } catch (FrontException e) {
            throw new ServletException(e);
        }
        log.info("End execute SecurityFilter ... ");
    }

    @Override
    public void destroy() {

    }
}