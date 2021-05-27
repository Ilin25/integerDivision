package ru.ilin.integerdivision;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class DivisionData {

    private Integer divisible;
    private Integer divisor;
    private Map<Integer, Integer> intermediateDivisionResults = new TreeMap<>();
    private StringBuilder resultDivision;

    public Integer getDivisible() {
        return divisible;
    }

    public void setDivisible(Integer divisible) {
        this.divisible = divisible;
    }

    public Integer getDivisor() {
        return divisor;
    }

    public void setDivisor(Integer divisor) {
        this.divisor = divisor;
    }

    public Map<Integer, Integer> getIntermediateDivisionResults() {
        return intermediateDivisionResults;
    }

    public void setIntermediateDivisionResults(Map<Integer, Integer> intermediateDivisionResults) {
        this.intermediateDivisionResults = intermediateDivisionResults;
    }

    public StringBuilder getResultDivision() {
        return resultDivision;
    }

    public void setResultDivision(StringBuilder resultDivision) {
        this.resultDivision = resultDivision;
    }

    public void addIntermediateResultDivision(Integer key, Integer value){
        intermediateDivisionResults.put(key, value);
    }

    public void addResultDivision(Integer value){
        resultDivision.append(value);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivisionData)) return false;
        DivisionData that = (DivisionData) o;
        return
                Objects.equals(getDivisible(), that.getDivisible()) &&
                Objects.equals(getDivisor(), that.getDivisor()) &&
                Objects.equals(getIntermediateDivisionResults(), that.getIntermediateDivisionResults()) &&
                Objects.equals(getResultDivision().toString(), that.getResultDivision().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDivisible(), getDivisor(), getIntermediateDivisionResults(), getResultDivision());
    }
}
