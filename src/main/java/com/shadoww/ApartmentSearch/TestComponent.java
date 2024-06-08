package com.shadoww.ApartmentSearch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestComponent {

    private long timeWorking = System.currentTimeMillis();
//    @Scheduled(fixedRate = 10 * 1000)
    public void test() {
        System.out.println("It work!");

        long current = System.currentTimeMillis();

        System.out.println((current - timeWorking) / 1000);

        timeWorking = current;
    }
}
