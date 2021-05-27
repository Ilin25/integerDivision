package ru.ilin.integerdivision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class FormatterTests {

    private final Formatter formatter = new Formatter();

    @Test
    void format_AnyIntegerNumberByZero_DivisionByZeroError() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(0);
        Assertions.assertThrows(ArithmeticException.class, ()->formatter.format(divisionData));
    }

    @Test
    void format_ZeroByAnyIntegerNumber_Zero() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(0);
        divisionData.setDivisor(78945);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(0));
        Assertions.assertEquals(formatter.format(divisionData),
                "0",
                "Divisible by zero divided by an integer.");
    }

    @Test
    void format_DivideNumberByItSelf_One() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(78945);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(1));
        Assertions.assertEquals(formatter.format(
                divisionData),
                "_78945|78945\n" +
                        " 78945|1\n" +
                        "     0\n",
                "The divisible is equal to the divisor");
    }

    @Test
    void format_AnyIntegerNumberByOne_IntegerNumber() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(1);
        divisionData.setIntermediateDivisionResults(new TreeMap<>());
        divisionData.setResultDivision(new StringBuilder().append(78945));
        Assertions.assertEquals
                (formatter.format(divisionData),
                "_78945|1\n" +
                        " 78945|78945\n" +
                        "     0\n",
                "The divisible is one.");
    }

    @Test
    void format_AnyIntegerNumberByAnyIntegerNumber_AnyIntegerNumber() {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(78945);
        divisionData.setDivisor(4);

        TreeMap<Integer, Integer> intermediateDivisionResults = new TreeMap<>();
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
        divisionData.setResultDivision(new StringBuilder().append(78945 / 4));
        Assertions.assertEquals(formatter.format(
                divisionData),
                "_78945|4\n" +
                        " 4    |19736\n" +
                        "_38\n" +
                        " 36\n" +
                        " _29\n" +
                        "  28\n" +
                        "  _14\n" +
                        "   12\n" +
                        "   _25\n" +
                        "    24\n" +
                        "     1\n",
                "Dividing integers");
    }
}
