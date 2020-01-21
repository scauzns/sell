package scau.zns.selladmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("scau.zns.selladmin.feign")
public class SellAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellAdminApplication.class, args);
    }

}
