package com.company.domain;

import com.company.data.FileHandler;
import com.company.member.AgeRange;
import com.company.member.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void totalContingent() {

        Calculator calc = new Calculator();
        calc.addTotal(calc.contingentCalculator(AgeRange.SENIOR, true));
        assertEquals(1600, calc.getTotal());
    }
}