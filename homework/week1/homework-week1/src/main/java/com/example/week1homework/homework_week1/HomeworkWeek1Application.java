package com.example.week1homework.homework_week1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// This is the HomeworkWeek1Application class
public class HomeworkWeek1Application implements CommandLineRunner {

    private final CakeBaker cakeBaker;

    public HomeworkWeek1Application(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeworkWeek1Application.class, args);
    }

    @Override
    public void run(String... args) {
        cakeBaker.bakeCake();
    }
}
