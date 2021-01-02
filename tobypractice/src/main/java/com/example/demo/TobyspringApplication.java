package com.example.demo;

import org.junit.runner.JUnitCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

public class TobyspringApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JUnitCore.main("com.example.demo.UserDaoTest");
    }

}
