package com.example;

import org.testng.annotations.Test;
public class AppTest {
    @Test(groups = "test")//пометка методов как тестовых методов
    public void testApp() {
        System.out.println("Всё работает");
    }
}
