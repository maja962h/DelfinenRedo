package com.company.member;

public class Member implements Comparable {

    protected String name;
    protected int age;
    protected boolean activeStatus;
    protected String ageRange;
    protected String competitiveStatus;
    private AgeRange eAgeRange;


    public Member(){

    }

    public Member(Member clone){
        this.name = clone.name;
        this.age = clone.age;
        this.activeStatus = clone.activeStatus;
        this.ageRange = clone.ageRange;
        this.competitiveStatus = clone.competitiveStatus;
        this.eAgeRange = clone.eAgeRange;
    }

    public Member(String name, int age, String ageRange, boolean activeStatus, String competitiveStatus, AgeRange eAgeRange){
        this.name = name;
        this.age = age;
        this.activeStatus = activeStatus;
        this.ageRange = ageRange;
        this.competitiveStatus = competitiveStatus;
        this.eAgeRange = eAgeRange;
    }

    public Member(String name, int age, String ageRange){
        this.name = name;
        this.age = age;
        this.ageRange = ageRange;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //TODO: delete if not used
    public String getCompetitiveStatus() {
        return competitiveStatus;
    }


    public void setCompetitiveStatus(String competitiveStatus) {
        this.competitiveStatus = competitiveStatus;
    }

    @Override
    public String toString() {
        return name + ": " + ageRange + ". " + activeStatus + " " + competitiveStatus + "\n";
    }

    @Override
    public int compareTo(Object other) {
        Member anotherMember = (Member) other; // Typecasting
        return name.compareTo(anotherMember.name);
    }

    public String getActiveStatusText() {
        if(!activeStatus()){
            return "passive";
        } else {
            return "active";
        }
    }

    public boolean activeStatus(){
            return this.activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        if(activeStatus.equals('p') || activeStatus.equals("passive")){
            this.activeStatus = false;
        } else if(activeStatus.equals("a") || activeStatus.equals("active")){
            this.activeStatus = true;
        }
    }

    public AgeRange getEnumAgeRange(){
        return eAgeRange;
    }

    public void setEnum(String ageRange) {
        this.eAgeRange = AgeRange.valueOf(ageRange);
    }

    public String toMemberFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + ";");
        sb.append(ageRange.toUpperCase() + ";");
        sb.append(getActiveStatusText() + ";");
        sb.append(competitiveStatus + ";");
        sb.append("\n");
        return sb.toString();
    }

    public String toMemberString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " ");
        sb.append(ageRange.toLowerCase() + " ");
        sb.append(getActiveStatusText() + " ");
        sb.append(competitiveStatus + " ");
        sb.append("\n");
        return sb.toString();
    }

}
