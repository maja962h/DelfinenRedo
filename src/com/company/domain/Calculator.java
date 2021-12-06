package com.company.domain;

import com.company.data.FileHandler;
import com.company.member.AgeRange;
import com.company.member.Member;

import java.util.ArrayList;

public class Calculator {

    private double total;
    private int juniorCon = 1000;
    private int seniorCon = 1600;
    private int passiveCon = 500;
    private double overSixty = seniorCon * 0.75;

    public double contingentCalculator(AgeRange eAgeRange, boolean isActive){

        double contingentSum = 0;
        if(!isActive){
            contingentSum = passiveCon;
        } else if(eAgeRange.equals(AgeRange.JUNIOR)){
            contingentSum = juniorCon;
        } else if(eAgeRange.equals(AgeRange.SENIOR)){
            contingentSum = seniorCon;
        }else if (eAgeRange.equals(AgeRange.ELDER)){
            contingentSum = overSixty;
        }
        return contingentSum;
    }

    public double addTotal(double fee){
        return this.total = total + fee;
    }

    public double getTotal() {
        return total;
    }

    public void totalContingent(){
        FileHandler fh = new FileHandler();
        fh.readFile();
        ArrayList<Member> allMembers = fh.getMemberList();
        for (Member member : allMembers){
            addTotal(contingentCalculator(member.getEnumAgeRange(), member.activeStatus()));
        }
    }

}
