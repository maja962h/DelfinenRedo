package com.company.member;

public class Member implements Comparable {

    protected String name;
    protected int age;
    protected boolean activeStatus;
    protected String ageRange;
    protected String competitiveStatus;


    public Member(){

    }

    public Member(Member clone){
        this.name = clone.name;
        this.age = clone.age;
        this.activeStatus = clone.activeStatus;
        this.ageRange = clone.ageRange;
        this.competitiveStatus = clone.competitiveStatus;

    }

    public Member(String name, int age, String ageRange, boolean activeStatus, String competitiveStatus){
        this.name = name;
        this.age = age;
        this.activeStatus = activeStatus;
        this.ageRange = ageRange;
        this.competitiveStatus = competitiveStatus;
    }

    public Member(String name, int age, String ageRange){
        this.name = name;
        this.age = age;
        this.ageRange = ageRange;
    }

    public Member(String name, String ageRange, boolean activeStatus, String competitiveStatus){
        this(name,0,ageRange,activeStatus,competitiveStatus);
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

   /* public String getActiveStatus() {
        return activeStatus;
    }*/

    public String getCompetitiveStatus() {
        return competitiveStatus;
    }

    /*public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }*/

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
        if (activeStatus.equals("passive")){
            this.activeStatus = false;
        } else if (activeStatus.equals("active")){
            this.activeStatus = true;
        }
    }

}
