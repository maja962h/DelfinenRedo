package com.company.member;

import java.util.ArrayList;

public class Competitor extends Member{

    private ArrayList<Discipline> disciplines;
    //private TrainingResults tr = new TrainingResults();

    public Competitor(Member member, ArrayList<Discipline> disciplines){
        super(member);
        this.disciplines = disciplines;
    }

   /* public Competitor(Member member, TrainingResults tr){
        super(member);
        this.tr = tr;
    }*/


    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }


    @Override
    public String toString() {
        return name + ": " + age + " år, " + ageRange + ". Discipline(s): " + disciplines +  "\n";
    }

}
