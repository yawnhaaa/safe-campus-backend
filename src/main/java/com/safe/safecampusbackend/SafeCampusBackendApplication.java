package com.safe.safecampusbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafeCampusBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafeCampusBackendApplication.class, args);
        System.out.println("------------------------------反诈校园，启动！-----------------------------");
    }

}
