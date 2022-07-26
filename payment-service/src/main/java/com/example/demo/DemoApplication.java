package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.NotNull;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

@NotNull @NotNull @NotNull
public void foo() {
        int myInteger = 0;
        {                      // violation
            myInteger = 2;
        }
        System.out.println("myInteger = " + myInteger);
        int a = 1;
        switch (a) {
            case 1: {                    // OK
                System.out.println("Case 1");
                break;
            }
            case 2:
                System.out.println("Case 2");     // OK
                break;
        }
    }

}
