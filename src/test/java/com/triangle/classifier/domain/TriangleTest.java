package com.triangle.classifier.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTest {

    @Test
    void createValidTriangleForValidInput(){
        BigDecimal a = new BigDecimal("3");
        BigDecimal b = new BigDecimal("4");
        BigDecimal c = new BigDecimal("5");

        assertDoesNotThrow(() -> new Triangle(a,b,c));
    }

    @Test
    void rejectNegativeSideA(){
        BigDecimal a = new BigDecimal("-3");
        BigDecimal b = new BigDecimal("0");
        BigDecimal c = new BigDecimal("0.000");

        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
        assertEquals("Side " + "a" + " has value of " + a + " . Value should be positive",
                exception.getMessage());
    }

    @Test
    void rejectZeroSideB(){
        BigDecimal a = new BigDecimal("3");
        BigDecimal b = new BigDecimal("0");
        BigDecimal c = new BigDecimal("4");

        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
        assertEquals("Side " + "b" + " has value of " + b + " . Value should be positive",
                exception.getMessage());
    }

    @Test
    void rejectZeroWithDecimalsSideC(){
        BigDecimal a = new BigDecimal("3");
        BigDecimal b = new BigDecimal("1");
        BigDecimal c = new BigDecimal("0.000");

        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
        assertEquals("Side " + "c" + " has value of " + c + " . Value should be positive",
                exception.getMessage());
    }

    @Test
    void rejectTooManyDigits(){
        BigDecimal a = new BigDecimal("1.00000000000000000000000000000000");
        BigDecimal b = new BigDecimal("1");
        BigDecimal c = new BigDecimal("1");

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));

    }

    @Test
    void rejectTooManyDecimalPlaces(){
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("0.000000000000000000000000000000001");
        BigDecimal c = new BigDecimal("1");

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));

    }

    @Test
    void rejectValueTooLarge(){
        BigDecimal a = new BigDecimal("1E+33");
        BigDecimal b = new BigDecimal("1E+33");
        BigDecimal c = new BigDecimal("1E+33");

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
    }

    @Test
    void rejectNullValue(){
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("4");
        BigDecimal c = null;

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
    }

    @Test
    void rejectInvalidTriangle(){
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("10");
        BigDecimal c = new BigDecimal("2");

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
    }

    @Test
    void rejectFlatTriangle(){
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        BigDecimal c = new BigDecimal("3");

        assertThrows(InvalidTriangleException.class, () -> new Triangle(a,b,c));
    }

}
