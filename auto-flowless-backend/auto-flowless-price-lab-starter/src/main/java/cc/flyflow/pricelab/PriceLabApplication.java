package cc.flyflow.pricelab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceLabApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceLabApplication.class, args);
    }
}
