package com.example.week1homework.homework_week1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// This is the HomeworkWeek1Application class
public class HomeworkWeek1Application implements CommandLineRunner {

    private final Frosting frosting;
    private final Syrup syrup;

    public HomeworkWeek1Application(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("--------Baking the cake------\nFrosting Type: " + frosting.getFrostingType() + "\n Syrup Type: " + syrup.getSyrupType() + "\n-----------Finished Baking the Cake, Enjoy!!!---------------");
    }

    public static void main(String[] args) {
        SpringApplication.run(HomeworkWeek1Application.class, args);
    }

    @Override
    public void run(String... args) {
        bakeCake();
    }
}
