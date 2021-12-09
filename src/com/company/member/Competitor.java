// @Maja
// @Christopher

package com.company.member;

import java.util.ArrayList;

public class Competitor extends Member {

    private ArrayList<Discipline> disciplines;

    public Competitor() {

    }

    public Competitor(Member member, ArrayList<Discipline> disciplines) {
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
        String[] list = test[4].substring(1, test[4].length() - 1).split(", ");
        for (String discipline : list) {
            disciplines.add(Discipline.valueOf(discipline.trim()));
        }
    }

    public String makeStringCompetitorShort() {
        return name;
    }

    public String toCompFileString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ";");
        stringBuilder.append(ageRange + ";");
        stringBuilder.append(getActiveStatusText() + ";");
        stringBuilder.append(competitiveStatus + ";");
        stringBuilder.append(disciplines);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toCompString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " ");
        stringBuilder.append(ageRange + " ");
        stringBuilder.append(disciplines);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return name + ": " + age + " år, " + ageRange + ". Discipline(s): " + disciplines + "\n";
    }

}
