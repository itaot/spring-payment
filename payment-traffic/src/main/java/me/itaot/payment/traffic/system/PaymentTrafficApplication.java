package me.itaot.payment.traffic.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentTrafficApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentTrafficApplication.class, args);
    }
}
