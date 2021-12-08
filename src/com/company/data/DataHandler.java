package com.company.data;
import com.company.member.Competitor;
import com.company.member.Member;

public class
DataHandler {
    FileHandler fileHandler = new FileHandler();

    //TODO: delete if not used
    public Member findMember(String name){
        for (Member member: fileHandler.getMemberList()) {
            if(member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public Competitor findCompetitor(String name){
        for (Competitor competitor: fileHandler.getCompetitors()) {
            if(competitor.getName().equalsIgnoreCase(name.trim())){
                return competitor;
            }
        }
        return null;
    }
}
