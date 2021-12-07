package com.company.data;
import com.company.member.AgeRange;
import com.company.member.Competitor;
import com.company.member.Member;
import com.company.member.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler implements DataInterface {

    private final ArrayList<Member> memberList = new ArrayList<>();
    private final ArrayList<Competitor> competitors = new ArrayList<>();
    private final ArrayList<Result> results = new ArrayList<>();

    public FileHandler() {
        readFile();
        readCompetitorFile();
        readResultFile();
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public ArrayList<Member> getMemberList() {

        return memberList;
    }

    //TODO: move to memberController
    public void addNewMember(Member member){
        memberList.add(member);
    }

    //TODO: move to memberController
    public void removeMember(int member){
        memberList.remove(member);
        saveMember();
        System.out.println("done");
    }

    //TODO: move to memberController
    public void addNewResult(Result tr){
        results.add(tr);
    }

    public void saveMember(){
        File file = new File("data/members.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Member member : memberList){
                fileWriter.append(member.toMemberFileString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCompetitor(){
        File file = new File("data/competitors.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Competitor competitor : competitors){
                fileWriter.append(competitor.toCompFileString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //TODO: move?
    public boolean authenticated(String name, String password, String role){
        boolean isAuthenticated = false;

        File file = new File("data/users.csv");

        try{
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()){
                String line = myReader.nextLine();
                String[] values = line.split(";");

                if(values[0].equals(name) && values[1].equals(password) && values[2].equals(role)){
                    isAuthenticated = true;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    public void saveResults(){
        File file = new File("data/results.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, true);

            for(Result result : results){

                    fileWriter.append(result.toCompetitionString());

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: make method void since the return value is never used?
    public ArrayList<Member> readFile(){
        try {
            Scanner myReader = new Scanner(new File("data/members.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] test = data.split(";");
                Member member = new Member(test[0],0, test[1], false, test[3], AgeRange.JUNIOR);
                member.setEnum(test[1]);
                member.setActiveStatus(test[2]);
                memberList.add(member);
            }
            return memberList;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    //TODO: make method void since the return value is never used?
    public ArrayList<Competitor> readCompetitorFile(){
        try {
            Scanner myReader = new Scanner(new File("data/competitors.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Competitor competitor = new Competitor(data);
                competitors.add(competitor);
            }
            return competitors;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Result> readResultFile(){
        try {
            Scanner myReader = new Scanner(new File("data/results.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Result result = new Result(data, competitors.get(0));
                results.add(result);
            }
            return results;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    //TODO: move to memberController
    public String makeStringMember(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Member member : memberList) {
            stringBuilder.append(member.toMemberString());
        }
        return stringBuilder.toString();
    }

    //TODO: move to memberController
    public String makeStringCompetitor(){

        StringBuilder stringBuilder = new StringBuilder();


        for (Competitor competitor : competitors) {
            stringBuilder.append(competitor.toCompString());
        }
        return stringBuilder.toString();
    }


    public ArrayList<Competitor> getCompetitors() {

        return competitors;
    }

    //TODO: move to memberController
    public void addNewCompetitor(Competitor competitor){

        competitors.add(competitor);
    }


}
