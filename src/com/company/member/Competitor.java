package com.company.member;

import java.util.ArrayList;

public class Competitor extends Member{

    private ArrayList<Discipline> disciplines;

    public Competitor(Member member, ArrayList<Discipline> disciplines){
        super(member);
        this.disciplines = disciplines;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public String toString() {
        return name + ": " + age + " år, " + ageRange + ". Discipline(s): " + disciplines +  "\n";
    }

}
