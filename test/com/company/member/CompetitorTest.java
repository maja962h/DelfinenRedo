package com.company.member;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompetitorTest {

  @Test
    void findCompetitor() {
        MemberController memberController = new MemberController();
        Competitor competitor = memberController.findCompetitor("Maja");

        assertNotNull(competitor);
    }

   @Test
    void readFile(){
       MemberController memberController = new MemberController();
        assertNotNull(memberController.getResults());
    }

    @Test
    void getCompetitors() {
        MemberController memberController = new MemberController();
        ArrayList<Competitor> liste = memberController.getCompetitors();

        assertEquals(9,liste.size());
    }

}