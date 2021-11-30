package com.company.member;

public class Competitor extends Member{

    private String discipline;

    public Competitor(String name, int age, String ageRange, String activeStatus, String competitiveStatus, String discipline){
        super(name, age, ageRange, activeStatus, competitiveStatus);
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return name + ": " + age + " år, " + ageRange + ". Discipline(s): " + discipline +  "\n";
    }

}
