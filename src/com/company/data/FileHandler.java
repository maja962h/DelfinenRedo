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

    private ArrayList<Competitor> competitors = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();

    public FileHandler() {
        //readFile();
        readCompetitorFile();
        readResultFile();
    }

    public void saveMember(ArrayList<Member> memberList){
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

    public void saveResults(){
        File file = new File("data/results.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Result result : results){

                fileWriter.append(result.toCompetitionString());

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> readFile(){
        try {
            Scanner myReader = new Scanner(new File("data/members.csv"));
            ArrayList<Member> allMembers = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] test = data.split(";");
                Member member = new Member(test[0],0, test[1], false, test[3], AgeRange.JUNIOR);
                member.setEnum(test[1]);
                member.setActiveStatus(test[2]);
                allMembers.add(member);
            }
            return allMembers;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public void readCompetitorFile(){
        try {
            Scanner myReader = new Scanner(new File("data/competitors.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Competitor competitor = new Competitor(data);
                competitors.add(competitor);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readResultFile(){
        try {
            Scanner myReader = new Scanner(new File("data/results.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Result result = new Result(data, competitors.get(0));
                results.add(result);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public ArrayList<Result> getResults() {
        return results;
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

}
