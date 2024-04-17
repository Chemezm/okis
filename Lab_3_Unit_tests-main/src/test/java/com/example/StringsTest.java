package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringsTest {
    @Test(groups = "strings")
    public void testArgs() {
        char[][] dataOne = {{'f','*'}, {'f','-'}};
        int actualCount = Strings.countArgs(dataOne);
        Assert.assertEquals(actualCount,3);
    }

    @Test(groups = "strings_wide")
    public void testChange() {
        String s= "Мама мыла раму в ООО «Рама», а папа мыл машину в ЗАО «Машина».";
        Assert.assertEquals(Strings.change(s), "Мама мыла раму в ООО «фирма», а папа мыл машину в ЗАО «фирма».");
    }

    @Test(groups = "strings_reverse")
    public void testReverse() {
        String s = "Присоединение";
        String expected = "еиненидеосирП";
        String actual = Strings.reverse(s);
        Assert.assertEquals(actual, expected);
    }


}
