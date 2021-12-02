package com.company.data;
import com.company.member.Competitor;
import com.company.member.Member;

public class Database {
    FileHandler fh = new FileHandler();

    public Member findMember(String name){
        for (Member member: fh.getMemberList()) {
            if(member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public Competitor findCompetitor(String name){
        for (Competitor competitor: fh.getCompetitors()) {
            if(competitor.getName().equals(name)){
                return competitor;
            }
        }
        return null;
    }
}
