package me.itaot.payment.cpcn;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = {"me.itaot"})
@ComponentScan(value = {"me.itaot"})
@EntityScan(basePackages = {"me.itaot"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceHealthIndicatorAutoConfiguration.class})
@EnableDubboConfiguration
public class PaymentCpcnApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentCpcnApplication.class, args);
    }
}