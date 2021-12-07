package com.company.domain;

import com.company.member.AgeRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void totalContingent() {

        Calculator calc = new Calculator();
        calc.addTotal(calc.contingentCalculator(AgeRange.SENIOR, true));
        calc.addTotal(calc.contingentCalculator(AgeRange.JUNIOR, true));
        assertEquals(2600, calc.getTotal());
    }
}