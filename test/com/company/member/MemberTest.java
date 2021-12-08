package com.company.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void setEnum() {
        Member member = new Member("Chris",0, "senior", true, "exerciser", AgeRange.JUNIOR);
        member.setEnum("SENIOR");
        assertEquals(AgeRange.SENIOR, member.getEnumAgeRange());
    }
}