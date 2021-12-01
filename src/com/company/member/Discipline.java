package com.company.member;

import java.util.ArrayList;

public enum Discipline {
    BUTTERFLY("butterfly"),
    CRAWL("crawl"),
    BACKCRAWL("backcrawl"),
    BREASTSTROKE("breaststroke");

    private String disciplineName;

    Discipline(String disciplineName){
        this.disciplineName = disciplineName;
    }

    Discipline() {

    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public ArrayList<Discipline> getDisciplines(){
        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BUTTERFLY);
        disciplines.add(Discipline.CRAWL);
        disciplines.add(Discipline.BACKCRAWL);
        disciplines.add(Discipline.BREASTSTROKE);
        return disciplines;
    }
}
