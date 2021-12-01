package com.company.domain;

import com.company.data.FileHandler;
import com.company.member.Member;

import java.util.ArrayList;

public class Calculator {

    private double total;
    private double totalDebt;
    private int junior = 17;
    private int senior = 18;
    private int elder = 60;
    private int juniorCon = 1000;
    private int seniorCon = 1600;
    private int passiveCon = 500;
    private double overSixty = seniorCon * 0.75;

    public Calculator(){
        totalContingent();
    }

    public double contingentCalculator(String age, boolean isActive){
        int ageNumber = 0;
        if(age.equals(junior)){
            ageNumber = 15;
        } else if(age.equals(senior)) {
            ageNumber = 20;
        } else if(age.equals(elder)){
            ageNumber = 60;
        }

        double contingentSum = 0;
        if(!isActive){
            contingentSum = passiveCon;
        } else if(ageNumber <= junior){
            contingentSum = juniorCon;
        } else if(ageNumber >= senior){
            contingentSum = seniorCon;
        }else if (ageNumber >= elder){
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
        double tempCon = 0;
        double tempTotal = 0;
        FileHandler fh = new FileHandler();
        fh.readFile();
        ArrayList<Member> allMembers = fh.getMemberList();
        //System.out.println(allMembers.size());
        for (Member member : allMembers){
            tempTotal = addTotal(contingentCalculator(member.getAgeRange(), member.activeStatus()));
            //tempCon = contingentCalculator(member.getAgeRange(), member.activeStatus());
            //tempTotal += tempCon;

        }
        total = tempTotal;
    }

}
