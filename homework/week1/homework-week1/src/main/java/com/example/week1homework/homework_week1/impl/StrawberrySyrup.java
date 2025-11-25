package com.example.week1homework.homework_week1.impl;

import com.example.week1homework.homework_week1.Syrup;
import com.example.week1homework.homework_week1.SyrupType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.syrup", havingValue = "strawberry")
public class StrawberrySyrup implements Syrup {
    @Override
    public SyrupType getSyrupType() {
        return SyrupType.STRAWBERRY;
    }
}
