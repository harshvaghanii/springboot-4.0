package com.example.week1homework.homework_week1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "chocolate")
public class ChocolateCake implements Frosting, Syrup {
    @Override
    public FrostingType getFrostingType() {
        return FrostingType.CHOCOLATE;
    }

    @Override
    public SyrupType getSyrupType() {
        return SyrupType.CHOCOLATE;
    }
}
