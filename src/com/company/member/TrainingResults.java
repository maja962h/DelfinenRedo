package com.company.member;

import java.time.LocalDate;

public class TrainingResults {

    private Competitor competitor;
    private LocalDate date;
    private Discipline discipline;
    private double swimTime;

    public TrainingResults(Competitor competitor, LocalDate date, Discipline discipline, double swimTime) {
        this.competitor = competitor;
        this.date = date;
        this.discipline = discipline;
        this.swimTime = swimTime;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public double getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(double swimTime) {
        this.swimTime = swimTime;
    }

    @Override
    public String toString() {
        return competitor.getName() + ": " + date + " - " + discipline + ": " + swimTime;
    }
}
