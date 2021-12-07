package com.company.domain;

import com.company.data.FileHandler;
import com.company.member.AgeRange;
import com.company.member.Member;

import java.util.ArrayList;

public class Calculator {

    private double total;
    private final int juniorCon = 1000;
    private final int seniorCon = 1600;
    private final int passiveCon = 500;
    private final double overSixty = seniorCon * 0.75;

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

    //TODO: make method void since the return value is never used?
    public double addTotal(double fee){
        return this.total = total + fee;
    }

    public double getTotal() {
        return total;
    }

    public void totalContingent(FileHandler fileHandler){
        fileHandler.readFile();
        ArrayList<Member> allMembers = fileHandler.getMemberList();
        for (Member member : allMembers){
            addTotal(contingentCalculator(member.getEnumAgeRange(), member.activeStatus()));
        }
    }

}
