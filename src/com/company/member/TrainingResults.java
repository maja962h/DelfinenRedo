package com.company.member;
import java.time.Duration;
import java.time.LocalDate;

public class TrainingResults implements Comparable<TrainingResults> {

    private Competitor competitor;
    private LocalDate date;
    private Discipline discipline;
    private Duration swimTime;

    public TrainingResults(LocalDate date, Discipline discipline, Duration swimTime) {
        this.date = date;
        this.discipline = discipline;
        this.swimTime = swimTime;
    }

    public TrainingResults(Competitor competitor, LocalDate date, Discipline discipline, Duration swimTime) {
        this.competitor = competitor;
        this.date = date;
        this.discipline = discipline;
        this.swimTime = swimTime;
    }

    public TrainingResults(){

    }

    public Competitor getCompetitor(){
        return competitor;
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


    @Override
    public String toString() {
        return date + " - " + discipline + ": " + swimTime;
    }

    @Override
    public int compareTo(TrainingResults other) {

       if (this.getSwimTime().toSeconds() == 0 && other.getSwimTime().toSeconds() == 0) {
            return 0;
        } else if (this.getSwimTime().toSeconds() == 0) {
            return 1;
        } else if (other.getSwimTime().toSeconds() == 0) {
            return -1;
        }

        return swimTime.compareTo(other.getSwimTime());
    }


}
