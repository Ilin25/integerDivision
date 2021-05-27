package ru.ilin.integerdivision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

class DivisionTests {

    private final Division division = new Division();

    @Test
    void divide_AnyIntegerNumberByZero_DivisionByZeroError() {
        Assertions.assertThrows(
                ArithmeticException.class, ()-> division.divide(78945,0));
    }

    @Test
    void divide_ZeroByAnyIntegerNumber_Zero() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(0);
        divisionData.setDivisor(78945);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(0));
        Assertions.assertEquals(division.divide(

                0, 78945),
                divisionData,
                "Divisible by zero divided by an integer.");
    }

    @Test
    void divide_DivideNumberByItSelf_One() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(78945);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(1));

        Assertions.assertEquals(division.divide(
                78945, 78945),
                divisionData,
                "The divisible is equal to the divisor");
    }

    @Test
    void divide_AnyIntegerNumberByOne_IntegerNumber() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(1);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(78945));
        Assertions.assertEquals(division.divide(

                78945, 1),
                divisionData,
                "The divisible is one.");
    }

    @Test
    void divide_AnyIntegerNumberByAnyIntegerNumber_AnyIntegerNumber() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(4);

        TreeMap <Integer,Integer> intermediateDivisionResults = new TreeMap<>();
        intermediateDivisionResults.put(1, 7);
        intermediateDivisionResults.put(2, 4);
        intermediateDivisionResults.put(3, 38);
        intermediateDivisionResults.put(4, 36);
        intermediateDivisionResults.put(5, 29);
        intermediateDivisionResults.put(6, 28);
        intermediateDivisionResults.put(7, 14);
        intermediateDivisionResults.put(8, 12);
        intermediateDivisionResults.put(9, 25);
        intermediateDivisionResults.put(10, 24);
        intermediateDivisionResults.put(11, 1);

        divisionData.setIntermediateDivisionResults(intermediateDivisionResults);
        divisionData.setResultDivision(new StringBuilder().append(78945/4));
        Assertions.assertEquals
                (division.divide(78945, 4), divisionData, "Dividing integers");
    }
}
