package com.company.member;

import java.util.ArrayList;

public class Competitor extends Member{

    private ArrayList<Discipline> disciplines;

    public Competitor(Member member, ArrayList<Discipline> disciplines){
        super(member);
        this.disciplines = disciplines;
    }

    public Competitor(String lineFromFile) {
        String[] test = lineFromFile.split(";");
        name = test[0];
        ageRange = test[1];
        setActiveStatus(test[2]);
        setCompetitiveStatus(test[3]);

        disciplines = new ArrayList<>();
        String[] list = test[4].substring(1,test[4].length()-1).split(", ");
        for( String discipline : list) {
            disciplines.add(Discipline.valueOf(discipline.trim()));
        }

    }

    public Competitor() {

    }

    public String makeStringCompetitorShort(){
        return name + ";" + ageRange ;
    }

    @Override
    public String toString() {
        return name + ": " + age + " år, " + ageRange + ". Discipline(s): " + disciplines +  "\n";
    }

    public String toCompFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + ";");
        sb.append(ageRange + ";");
        sb.append(getActiveStatusText() + ";");
        sb.append(competitiveStatus + ";");
        sb.append(disciplines);
        sb.append("\n");
        return sb.toString();
    }

    public String toCompString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " ");
        sb.append(ageRange + " ");
        sb.append(disciplines);
        sb.append("\n");
        return sb.toString();
    }



}
