package com.company.domain;

import com.company.data.FileHandler;
import com.company.member.Member;

public class Calculator {

    private int junior = 17;
    private int senior = 18;
    private int elder = 60;
    private int juniorCon = 1000;
    private int seniorCon = 1600;
    private int passiveCon = 500;
    private double overSixty = seniorCon * 0.75;

    public double contingentCalculator(int age, boolean isActive){
        double contingetSum = 0;
        if(!isActive){
            contingetSum = passiveCon;
        } else if(age <= junior){
            contingetSum = juniorCon;
        } else if(age >= senior){
            contingetSum = seniorCon;
        }else if (age >= elder){
            contingetSum = overSixty;
        }
        return contingetSum;
    }

    public double totalContingent(){
        double tempTotal = 0;
        FileHandler fh = new FileHandler();
        for (Member member : fh.getMemberList()){
            double tempCon = contingentCalculator(member.getAge(), member.activeStatus());
            tempTotal += tempCon;
        }
        return tempTotal;
    }

}
