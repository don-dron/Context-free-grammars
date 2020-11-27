package com.zvoa.maths.tfl.grammar.tests;

import org.junit.*;
import org.junit.Test;

public class AllTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before com.zvoa.maths.tfl.grammar.cf.tests.CalculatorTest.class");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After com.zvoa.maths.tfl.grammar.cf.tests.CalculatorTest.class");
    }

    @Before
    public void initTest() {
        //
    }

    @After
    public void afterTest() {
        //
    }

    @Test
    public void testGetSum() throws Exception {
       //
    }

    @Test
    public void testGetDivide() throws Exception {
       //
    }

    @Test
    public void testGetMultiple() throws Exception {

    }

    @Test
    public void divisionWithException() {
        //
    }

    @Ignore("Message for ignored test")
    @Test
    public void ignoredTest() {
        //
    }

    @Ignore("Message for ignored test")
    @Test(timeout = 500)
    public void timeStampTest() {
        while (true);
    }
}