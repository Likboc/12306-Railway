package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao.mapper")
public class TicketServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TicketServiceApplication.class,args);
    }
}
