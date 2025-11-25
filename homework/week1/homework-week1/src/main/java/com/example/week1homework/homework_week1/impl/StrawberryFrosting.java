package com.example.week1homework.homework_week1.impl;

import com.example.week1homework.homework_week1.Frosting;
import com.example.week1homework.homework_week1.FrostingType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.frosting", havingValue = "strawberry")
public class StrawberryFrosting implements Frosting {
    @Override
    public FrostingType getFrostingType() {
        return FrostingType.STRAWBERRY;
    }
}
