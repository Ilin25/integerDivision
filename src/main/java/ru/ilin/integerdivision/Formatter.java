package ru.ilin.integerdivision;

import java.util.Map;
import java.util.TreeMap;

public class Formatter {
    private static final String MINUS = "_";
    private static final String PIPE = "|";
    private static final String WHITESPACE = " ";

    public String format(DivisionData data) {
        StringBuilder secondLine = new StringBuilder();
        StringBuilder whiteSpaceEvenLines = new StringBuilder();
        StringBuilder whiteSpaceNonEvenLines = new StringBuilder();
        StringBuilder whiteSpaceRightInteger = new StringBuilder();
        String resultDivision = " ";
        Map<Integer, StringBuilder> formattedLinesResultsDivision = new TreeMap<>();

        if(data.getDivisor() == 0){
            throw new ArithmeticException();
        }

        if(data.getDivisible() < data.getDivisor()){
            resultDivision = data.getResultDivision().toString();
            return resultDivision;
        }

        if(data.getDivisor().equals(data.getDivisible())){
            resultDivision = formatWhenDivisibleEqualsDivisor(data,formattedLinesResultsDivision);
            return resultDivision;
        }

        if(data.getDivisor() == 1){
            resultDivision = formatWhenDivisorEqualsToOne(data,formattedLinesResultsDivision);
            return resultDivision;
        }


        for (int i = 1; i <= countWhiteSpaceBeforeThirdLine(data,i); i++) {
            whiteSpaceEvenLines.append(" ");
            whiteSpaceNonEvenLines.append(" ");
        }
        secondLine.append(whiteSpaceEvenLines).append(data.getIntermediateDivisionResults().get(2));

        for (int i = 1; i <= (data.getDivisible().toString().length() + 1)
                - (secondLine.length()); i++) {
            whiteSpaceRightInteger.append(" ");
        }
        secondLine.append(whiteSpaceRightInteger)
                .append(PIPE)
                .append(data.getResultDivision());
        formattedLinesResultsDivision.put(2, secondLine);

        formattedLinesResultsDivision.put(1, new StringBuilder().append(MINUS)
                .append(data.getDivisible())
                .append(PIPE)
                .append(data.getDivisor()));

        for (int i = 3; i <= data.getIntermediateDivisionResults().size(); i++) {
            StringBuilder intermediateBuffer = new StringBuilder();

            if (i % 2 != 0 && i < data.getIntermediateDivisionResults().size()) {
                intermediateBuffer.append(whiteSpaceEvenLines)
                        .replace(whiteSpaceEvenLines.length() - 1, whiteSpaceEvenLines.length(), "_")
                        .append(data.getIntermediateDivisionResults().get(i));
                formattedLinesResultsDivision.put(i, new StringBuilder(intermediateBuffer));
                whiteSpaceEvenLines.insert(0, " ");
                intermediateBuffer.setLength(0);
                continue;
            }

            intermediateBuffer.setLength(0);

            for (int j = 1; j < countWhitespaceAfterSecondLine(data, j); j++) {
                whiteSpaceNonEvenLines.append(" ");
            }
            intermediateBuffer
                    .append(whiteSpaceNonEvenLines)
                    .append(data.getIntermediateDivisionResults().get(i));
            formattedLinesResultsDivision.put(i, new StringBuilder(intermediateBuffer));
            whiteSpaceNonEvenLines.append(" ");
        }

        return mapToString(formattedLinesResultsDivision);
    }

    private int countWhitespaceAfterSecondLine(DivisionData data, int countLine){
        int lengthNonEvenLine = data.getIntermediateDivisionResults().get(countLine).toString().length() + 1;
        int lengthEvenLine = data.getIntermediateDivisionResults().get(countLine).toString().length();
        int amountWhiteSpaceNonEvenLines = lengthNonEvenLine - lengthEvenLine;
        return amountWhiteSpaceNonEvenLines;
    }

    private int countWhiteSpaceBeforeThirdLine(DivisionData data, int countLine){
        int lengthNonEvenLine = data.getIntermediateDivisionResults().get(1).toString().length() + 1;
        int lengthEvenLine = data.getIntermediateDivisionResults().get(2).toString().length();
        int amountWhiteSpaceNonEvenLines = lengthNonEvenLine - lengthEvenLine;
        return amountWhiteSpaceNonEvenLines;
    }

    private String formatWhenDivisibleEqualsDivisor(DivisionData data,
                                                Map<Integer, StringBuilder> formattedLinesResultsDivision){

        StringBuilder tempString = new StringBuilder();
        tempString.append(MINUS);
        tempString.append(data.getDivisible());
        tempString.append(PIPE);
        tempString.append(data.getDivisor());
        formattedLinesResultsDivision.put(1, new StringBuilder(tempString));

        tempString.setLength(0);
        tempString.append(WHITESPACE);
        tempString.append(data.getDivisible());
        tempString.append(PIPE);
        tempString.append(1);
        formattedLinesResultsDivision.put(2, new StringBuilder(tempString));

        tempString.setLength(0);
        tempString.append(WHITESPACE);
        for (int i = 0; i < String.valueOf(data.getDivisible()).length() - 1; i++) {
            tempString.append(WHITESPACE);
        }
        tempString.append(0);
        formattedLinesResultsDivision.put(3, new StringBuilder(tempString));

        return mapToString(formattedLinesResultsDivision);
    }

    private String formatWhenDivisorEqualsToOne(DivisionData data, Map<Integer, StringBuilder> formattedLinesResultsDivision){
        StringBuilder tempString = new StringBuilder();
        tempString.append(MINUS);
        tempString.append(data.getDivisible());
        tempString.append(PIPE);
        tempString.append(data.getDivisor());
        formattedLinesResultsDivision.put(1, new StringBuilder(tempString));

        tempString.setLength(0);
        tempString.append(WHITESPACE);
        tempString.append(data.getDivisible());
        tempString.append(PIPE);
        tempString.append(data.getResultDivision());
        formattedLinesResultsDivision.put(2, new StringBuilder(tempString));

        tempString.setLength(0);
        tempString.append(WHITESPACE);
        for (int i = 0; i < String.valueOf(data.getDivisible()).length() - 1; i++) {
            tempString.append(WHITESPACE);
        }
        tempString.append(0);
        formattedLinesResultsDivision.put(3, new StringBuilder(tempString));

        return mapToString(formattedLinesResultsDivision);
    }

    private String mapToString(Map<Integer, StringBuilder> formattedLinesResultsDivision){
        StringBuilder resultString = new StringBuilder();

        for (Map.Entry<Integer, StringBuilder> entry : formattedLinesResultsDivision.entrySet()){
            resultString.append(entry.getValue().append("\n"));
        }
        return resultString.toString();
    }
}
