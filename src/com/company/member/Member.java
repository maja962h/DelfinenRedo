// @Maja
// @Christopher
// @Kenneth
// @Güler


package com.company.member;

public class Member implements Comparable {

    protected String name;
    protected int age;
    protected boolean activeStatus;
    protected String ageRange;
    protected String competitiveStatus;
    private AgeRange eAgeRange;


    public Member() {

    }

    public Member(Member clone) {
        this.name = clone.name;
        this.age = clone.age;
        this.activeStatus = clone.activeStatus;
        this.ageRange = clone.ageRange;
        this.competitiveStatus = clone.competitiveStatus;
        this.eAgeRange = clone.eAgeRange;
    }

    public Member(String name, int age, String ageRange, boolean activeStatus, String competitiveStatus, AgeRange eAgeRange) {
        this.name = name;
        this.age = age;
        this.activeStatus = activeStatus;
        this.ageRange = ageRange;
        this.competitiveStatus = competitiveStatus;
        this.eAgeRange = eAgeRange;
    }

    public Member(String name, int age, String ageRange) {
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

    public void setName(String name) {
        this.name = name;
    }

    public AgeRange getEnumAgeRange() {
        return eAgeRange;
    }

    public void setEnum(String ageRange) {
        this.eAgeRange = AgeRange.valueOf(ageRange);
    }

    public void setCompetitiveStatus(String competitiveStatus) {
        this.competitiveStatus = competitiveStatus;
    }

    public String getActiveStatusText() {
        if (!activeStatus()) {
            return "passive";
        } else {
            return "active";
        }
    }

    public boolean activeStatus() {
        return this.activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        if (activeStatus.equals('p') || activeStatus.equals("passive")) {
            this.activeStatus = false;
        } else if (activeStatus.equals("a") || activeStatus.equals("active")) {
            this.activeStatus = true;
        }
    }

    public String toMemberFileString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ";");
        stringBuilder.append(ageRange.toUpperCase() + ";");
        stringBuilder.append(getActiveStatusText() + ";");
        stringBuilder.append(competitiveStatus + ";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toMemberString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " ");
        stringBuilder.append(ageRange.toLowerCase() + " ");
        stringBuilder.append(getActiveStatusText() + " ");
        stringBuilder.append(competitiveStatus + " ");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Object other) {
        Member anotherMember = (Member) other; // Typecasting
        return name.compareTo(anotherMember.name);
    }

    @Override
    public String toString() {
        return name + ": " + ageRange + ". " + activeStatus + " " + competitiveStatus + "\n";
    }
}
