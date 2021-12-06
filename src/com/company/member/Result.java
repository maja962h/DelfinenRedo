package com.company.member;
import java.time.Duration;
import java.time.LocalDate;

public class Result implements Comparable<Result> {

    private Competitor competitor;
    private LocalDate date;
    private Discipline discipline;
    private Duration swimTime;
    private String compName;


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
        this.compName = compName;
    }


    public String getCompetitor(){
        return competitor.makeStringCompetitorShort();
    }

    public LocalDate getDate() {
        return date;
    }


    public Discipline getDiscipline() {
        return discipline;
    }


    public Duration getSwimTime() {
        return swimTime;
    }

    public String getCompName() {
        return compName;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getCompetitor() + ";");
        sb.append(date + ";");
        sb.append(discipline + ";");
        sb.append(swimTime + ";");
        sb.append(compName + ";");
        sb.append("\n");
        return sb.toString();
    }

    public String toTrainingString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCompetitor() + ";");
        sb.append(date + ";");
        sb.append(discipline + ";");
        sb.append(swimTime + ";");
        sb.append("\n");
        return sb.toString();
    }

}
