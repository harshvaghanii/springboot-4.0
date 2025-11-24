package com.example.week1homework.homework_week1;

import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Frosting frosting;
    private final Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("--------Baking the cake------\nFrosting Type: " + frosting.getFrostingType() + "\n Syrup Type: " + syrup.getSyrupType() + "\n-----------Finished Baking the Cake, Enjoy!!!---------------");
    }

}
