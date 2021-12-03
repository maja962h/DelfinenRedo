package com.company.member;
import com.company.data.Database;
import com.company.data.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorTest {

    @Test
    void setEnum() {
        Database database = new Database();
        FileHandler fileHandler = new FileHandler();
        Competitor competitor = database.findCompetitor(fileHandler.getCompetitors(), "Maja");

        assertNotNull(competitor);
    }

    @Test
    void getCompetitors() {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Competitor> liste = fileHandler.getCompetitors();

        assertEquals(6,liste.size());
    }

}