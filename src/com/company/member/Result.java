// @Maja
// @Güler

package com.company.member;

import java.time.Duration;
import java.time.LocalDate;

public class Result implements Comparable<Result> {

    private Competitor competitor;
    private LocalDate date;
    private Discipline discipline;
    private Duration swimTime;
    private String competitionName;


    public Result(Competitor competitor, LocalDate date, Discipline discipline, Duration swimTime) {
        this.competitor = competitor;
        this.date = date;
        this.discipline = discipline;
        this.swimTime = swimTime;
    }

    public Result(Competitor competitor, LocalDate date, Discipline discipline, Duration swimTime, String compName) {
        this.competitor = competitor;
        this.date = date;
        this.discipline = discipline;
        this.swimTime = swimTime;
        this.competitionName = compName;
    }

    public Result(String lineFromFile, Competitor competitor) {
        this.competitor = competitor;
        String[] test = lineFromFile.split(";");
        competitor.setName(test[0]);
        date = LocalDate.parse(test[1]);
        discipline = Discipline.valueOf(test[2]);
        swimTime = Duration.parse(test[3]);
        competitionName = test[4];
    }

    public String toCompetitionString(String competitorName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(competitorName + ";");
        stringBuilder.append(date + ";");
        stringBuilder.append(discipline + ";");
        stringBuilder.append(swimTime + ";");
        stringBuilder.append(competitionName + ";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String getCompetitor() {
        return competitor.makeStringCompetitorShort();
    }

    public Duration getSwimTime() {
        return swimTime;
    }

    @Override
    public int compareTo(Result other) {

        if (this.getSwimTime().toSeconds() == 0 && other.getSwimTime().toSeconds() == 0) {
            return 0;
        } else if (this.getSwimTime().toSeconds() == 0) {
            return 1;
        } else if (other.getSwimTime().toSeconds() == 0) {
            return -1;
        }

        return swimTime.compareTo(other.getSwimTime());
    }

    @Override
    public String toString() {
        return date + " - " + discipline + ": " + swimTime;
    }
}
