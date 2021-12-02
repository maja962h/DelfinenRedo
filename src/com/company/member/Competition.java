package com.company.member;

import java.time.Duration;
import java.time.LocalDate;

public class Competition extends TrainingResults {

    private String competitionName;

    public Competition(LocalDate date, Discipline discipline, Duration swimTime, String competitionName) {
        super(date, discipline, swimTime);
        this.competitionName = competitionName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    @Override
    public String toString() {
        return competitionName + ". " + getDate() + " - " + getDiscipline() + ": " + getSwimTime();
    }
}
