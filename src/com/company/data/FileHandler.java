package com.company.data;
import com.company.domain.User;
import com.company.member.Competitor;
import com.company.member.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<Competitor> competitors = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>(); //TODO: do we need an arraylist or???


    public void addNewMember(Member member){
        memberList.add(member);
    }

    public void saveMember(){
        File file = new File("data/members.txt");

        try{
            FileWriter fileWriter = new FileWriter(file, true);

            for(Member member : memberList){
                fileWriter.append(member.getName() + ";");
                fileWriter.append(member.getAgeRange() + ";");
                fileWriter.append(member.getActiveStatus() + ";");
                fileWriter.append(member.getCompetitiveStatus());
                fileWriter.append("\n");
            }
            fileWriter.close();
            memberList.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveCompetitor(){
        File file = new File("data/competitors.txt");

        try{
            FileWriter fileWriter = new FileWriter(file, true);

            for(Competitor competitor : competitors){
                fileWriter.append(competitor.getName() + ";");
                fileWriter.append(competitor.getAgeRange() + ";");
                fileWriter.append(competitor.getActiveStatus() + ";");
                fileWriter.append(competitor.getCompetitiveStatus() + ";");
                fileWriter.append(competitor.getDiscipline());
                fileWriter.append("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Member> readFile(){
        File myObj = new File("data/members.txt");
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] test = data.split(";");
                Member member = new Member(test[0], test[1], test[2], test[3]);
                memberList.add(member);
            }
            return memberList;

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
        for (Member member : readFile()) {

            stringBuilder.append(member.getName()).append(" ");

            stringBuilder.append(member.getAgeRange()).append(" ");

            stringBuilder.append(member.getActiveStatus()).append(" ");

            stringBuilder.append(member.getCompetitiveStatus()).append("\n");

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

    //TODO: Güler laver denne metode!
    public User findUser(String name, String password) {
        return null;
    }

}
