package com.seesea.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class PiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

}
