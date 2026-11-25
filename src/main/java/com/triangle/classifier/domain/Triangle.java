package com.triangle.classifier.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Triangle(BigDecimal a, BigDecimal b, BigDecimal c) {

    private static final int MAX_PRECISION = 32;
    private static final int MAX_SCALE = 32;
    private static final BigDecimal MAX_VALUE = new BigDecimal("1e32");

    public Triangle {
        validateNull("a",a);
        validateNull("b",b);
        validateNull("c",c);

        validatePositive("a",a);
        validatePositive("b",b);
        validatePositive("c",c);

        validateSizeLimit("a",a);
        validateSizeLimit("b",b);
        validateSizeLimit("c",c);

        validateTriangleInequality(a,b,c);

    }

    private static void validateTriangleInequality(BigDecimal a, BigDecimal b, BigDecimal c) {
        List<BigDecimal> valueList = new ArrayList<>(List.of(a,b,c));
        valueList.sort(BigDecimal::compareTo);
        if (valueList.get(0).add(valueList.get(1)).compareTo(valueList.get(2)) <= 0)
            throw InvalidTriangleException.notATriangle(valueList.get(2));
    }

    private static void validatePositive(String side, BigDecimal value){
        if (value.compareTo(BigDecimal.ZERO) <= 0)
            throw InvalidTriangleException.nonPositiveSide(side,value);
    }

    private static void validateSizeLimit(String side, BigDecimal value){
        if (value.precision() > MAX_PRECISION ||
            value.scale() > MAX_SCALE || value.compareTo(MAX_VALUE) > 0)
                throw InvalidTriangleException.sideOutOfRange(side,value);
    }

    private static void validateNull(String side, BigDecimal value){
        if (value == null) throw InvalidTriangleException.missingSide(side,value);
    }
}
