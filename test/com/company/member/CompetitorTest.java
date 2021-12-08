package com.company.member;
import com.company.data.DataHandler;
import com.company.data.DataHandler;
import com.company.data.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorTest {

   @Test
    void setEnum() {
        DataHandler database = new DataHandler();
        Competitor competitor = database.findCompetitor("Maja");

        assertNotNull(competitor);
    }

    @Test
    void readFile(){
       FileHandler fileHandler = new FileHandler();
       assertNotNull(fileHandler.getResults());
    }

    @Test
    void getCompetitors() {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Competitor> liste = fileHandler.getCompetitors();

        assertEquals(9,liste.size());
    }

}