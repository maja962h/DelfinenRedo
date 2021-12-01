package com.company.data;
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


    public void addNewMember(Member member){
        memberList.add(member);
    }

    public void removeMember(int member){
        memberList.remove(member);
        saveMember();
        System.out.println("done");
    }

    public void saveMember(){
        File file = new File("data/members.csv");

        try{
            FileWriter fileWriter = new FileWriter(file, false);

            for(Member member : memberList){
                fileWriter.append(member.getName() + ";");
                fileWriter.append(member.getAgeRange() + ";");
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
            FileWriter fileWriter = new FileWriter(file, true);

            for(Competitor competitor : competitors){
                fileWriter.append(competitor.getName() + ";");
                fileWriter.append(competitor.getAgeRange() + ";");
                fileWriter.append(competitor.getActiveStatusText() + ";");
                fileWriter.append(competitor.getCompetitiveStatus() + ";");
                fileWriter.append(competitor.getDisciplines().toString());
                fileWriter.append("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Member> readFile(){
        File myObj = new File("data/members.csv");
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] test = data.split(";");
                Member member = new Member(test[0], test[1], false, test[3]);
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

    // Turns arraylist into string, so it can be printed to console.
    public String makeStringMember(){

        //Initializing a StringBuilder object.
        StringBuilder stringBuilder = new StringBuilder();

        //Loops through the list of members.
        for (Member member : memberList) {

            stringBuilder.append(member.getName()).append(" ");

            stringBuilder.append(member.getAgeRange()).append(" ");

            stringBuilder.append(member.getActiveStatusText()).append(" ");

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


}
