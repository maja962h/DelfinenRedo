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

    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<Competitor> competitors = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();

    public FileHandler() {
        readFile();
        readCompetitorFile();
    }

    public void addNewMember(Member member){
        memberList.add(member);
    }

    public void removeMember(int member){
        memberList.remove(member);
        saveMember();
        System.out.println("done");
    }

    public void addNewResult(Result tr){
        results.add(tr);
    }

    public void saveMember(){
        File file = new File("data/members.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Member member : memberList){
                fileWriter.append(member.getName() + ";");
                fileWriter.append(member.getAgeRange().toUpperCase() + ";");
                fileWriter.append(member.getActiveStatusText() + ";");
                fileWriter.append(member.getCompetitiveStatus());
                fileWriter.append("\n");
            }
            fileWriter.close();
            //memberList.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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



    public void saveCompetitor(){
        File file = new File("data/competitors.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Competitor competitor : competitors){
                fileWriter.append(competitor.toFileString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveResults(){
        File file = new File("data/results.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, true);

            for(Result trainingResults : results){
                fileWriter.append(trainingResults.getCompetitor() + ";");
                fileWriter.append(trainingResults.getDate() + ";");
                fileWriter.append(trainingResults.getDiscipline() + ";");
                fileWriter.append(trainingResults.getSwimTime() + ";");
                fileWriter.append(trainingResults.getCompName());
                fileWriter.append("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    // Turns arraylist into string, so it can be printed to console.
    public String makeStringMember(){

        //Initializing a StringBuilder object.
        StringBuilder stringBuilder = new StringBuilder();

        //Loops through the list of members.
        for (Member member : memberList) {

            stringBuilder.append(member.getName()).append(" ");

            stringBuilder.append(member.getAgeRange().toLowerCase()).append(" ");

            stringBuilder.append(member.getActiveStatusText()).append(" ");

            stringBuilder.append(member.getCompetitiveStatus()).append("\n");

        }
        return stringBuilder.toString();
    }

    // Turns arraylist into string, so it can be printed to console.
    public String makeStringCompetitor(){

        //Initializing a StringBuilder object.
        StringBuilder stringBuilder = new StringBuilder();

        //Loops through the list of members.
        for (Competitor competitor : competitors) {

            stringBuilder.append(competitor.getName()).append(" ");

            stringBuilder.append(competitor.getAgeRange().toLowerCase()).append(" ");

            stringBuilder.append(competitor.getDisciplines()).append("\n");

            //stringBuilder.append(member.getCompetitiveStatus()).append("\n");

        }
        return stringBuilder.toString();
    }


    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }


    public void addNewCompetitor(Competitor competitor){
        competitors.add(competitor);
    }


}
