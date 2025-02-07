package base.example.demo;


import base.example.demo.controller.Calculate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateTest {
    private Calculate calculate;
    @BeforeEach
    void setUp() {
        calculate = new Calculate();
    }



    @Test
    void sumTest(){

        int sum = calculate.sum(10,20);
        Assertions.assertEquals(30,sum);
    }

    @Test
    void everOrOdd_evenNumberTest(){

        boolean even = calculate.eveOrOdd(10);
        Assertions.assertTrue(even);
    }

    @Test
    void everOrOdd_oddNumberTest(){

        boolean add = calculate.eveOrOdd(15);
        Assertions.assertFalse(add);
    }
    @Test
    void divideByZeroTest(){
        Assertions.assertThrows(ArithmeticException.class, () -> calculate.divide(10,0));
    }

}
