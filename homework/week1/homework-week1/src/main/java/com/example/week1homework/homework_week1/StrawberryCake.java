package com.example.week1homework.homework_week1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "strawberry")
public class StrawberryCake implements Frosting, Syrup {
    @Override
    public FrostingType getFrostingType() {
        return FrostingType.STRAWBERRY;
    }

    @Override
    public SyrupType getSyrupType() {
        return SyrupType.STRAWBERRY;
    }
}
