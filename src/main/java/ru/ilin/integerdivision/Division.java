package ru.ilin.integerdivision;

import java.util.List;
import java.util.stream.Collectors;

public class Division {

    public DivisionData divide(int divisible, int divisor) {
        DivisionData divisionData = new DivisionData();
        divisionData.setDivisible(divisible);
        divisionData.setDivisor(divisor);
        divisionData.setResultDivision(new StringBuilder());

        if(divisor == 0){
            throw new ArithmeticException("Делимое равно 0.На ноль Делить нельзя");
        }
        if (divisible == divisor || divisor == 1 || divisible < divisor || divisor == 0)
        {
            divisionData.addResultDivision(divisible/divisor);
            return divisionData;
        }

        List<Character> divisibleList = convertDivisibleToList(divisible);

        int countIncompleteDivisible = 0;
        int incompleteDivisible = 0;
        int intermediateDivision = 0;
        int intermediateMultiplication = 0;
        int intermediateSubtraction = 0;
        int lineId = 0;

        incompleteDivisible = Integer.valueOf(divisibleList.get(countIncompleteDivisible).toString());

        while (countIncompleteDivisible != divisibleList.size()) {
            if (incompleteDivisible >= divisor) {
                divisionData.addIntermediateResultDivision(++lineId, incompleteDivisible);
            intermediateDivision = incompleteDivisible / divisor;
            intermediateMultiplication = divisor * intermediateDivision;
            intermediateSubtraction = incompleteDivisible - intermediateMultiplication;
            incompleteDivisible = intermediateSubtraction;
                divisionData.addIntermediateResultDivision(++lineId, intermediateMultiplication);
            }

            if (countIncompleteDivisible == divisibleList.size()-1){
                if (intermediateSubtraction != 0){
                    divisionData.addIntermediateResultDivision(++lineId, intermediateSubtraction);
                    divisionData.addResultDivision(divisible/divisor);
                }
                else {
                    divisionData.addIntermediateResultDivision(++lineId, incompleteDivisible);
                    divisionData.addResultDivision(divisible/divisor);
                }
                break;
            }

            if (incompleteDivisible < divisor){
                countIncompleteDivisible++;
            String incrementIncompleteDivisible = incompleteDivisible + divisibleList.get(countIncompleteDivisible).toString();
            incompleteDivisible = Integer.parseInt(incrementIncompleteDivisible);

            }
        }
        return  divisionData;
    }

    private List<Character> convertDivisibleToList(Integer divisible) {
        return divisible.toString()
                .chars()
                .mapToObj(c ->(char)c)
                .collect(Collectors.toList());
    }

}
