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

    public Result(String lineFromFile, Competitor competitor){

        String[] test = lineFromFile.split(";");
        //Competitor competitor = new Competitor();
        competitor.setName(test[0]);
        competitor.setAgeRange(test[1]);
        date = LocalDate.parse(test[2]);
        discipline = Discipline.valueOf(test[3]);
        swimTime = Duration.parse(test[4]);
        competitionName = test[5];
    }


    public String getCompetitor(){
        return competitor.makeStringCompetitorShort();
    }

    //TODO: delete if not used
    public LocalDate getDate() {
        return date;
    }

    //TODO: delete if not used
    public Discipline getDiscipline() {
        return discipline;
    }

    public Duration getSwimTime() {
        return swimTime;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    @Override
    public String toString() {
        return date + " - " + discipline + ": " + swimTime;
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

    public String toCompetitionString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCompetitor() + ";");
        stringBuilder.append(date + ";");
        stringBuilder.append(discipline + ";");
        stringBuilder.append(swimTime + ";");
        stringBuilder.append(competitionName + ";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toTrainingString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCompetitor() + ";");
        stringBuilder.append(date + ";");
        stringBuilder.append(discipline + ";");
        stringBuilder.append(swimTime + ";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
