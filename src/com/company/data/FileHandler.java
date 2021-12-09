// @Maja
// @Christopher
// @Kenneth
// @Güler

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

    public FileHandler() {

    }

    public void saveMember(ArrayList<Member> memberList) {
        File file = new File("data/members.csv");

        try {
            FileWriter fileWriter = new FileWriter(file, false);

            for (Member member : memberList) {
                fileWriter.append(member.toMemberFileString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCompetitor(ArrayList<Competitor> competitors) {
        File file = new File("data/competitors.csv");

        try {
            FileWriter fileWriter = new FileWriter(file, false);

            for (Competitor competitor : competitors) {
                fileWriter.append(competitor.toCompFileString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveResults(ArrayList<Result> results) {
        File file = new File("data/results.csv");

        try {
            FileWriter fileWriter = new FileWriter(file, false);

            for (Result result : results) {

                fileWriter.append(result.toCompetitionString());

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> readFile() {
        try {
            Scanner myReader = new Scanner(new File("data/members.csv"));
            ArrayList<Member> allMembers = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] test = data.split(";");
                Member member = new Member(test[0], 0, test[1], false, test[3], AgeRange.JUNIOR);
                member.setEnum(test[1]);
                member.setActiveStatus(test[2]);
                allMembers.add(member);
            }
            return allMembers;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");//TODO: fix or delete
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Competitor> readCompetitorFile() {
        try {
            Scanner myReader = new Scanner(new File("data/competitors.csv"));
            ArrayList<Competitor> allCompetitors = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Competitor competitor = new Competitor(data);
                allCompetitors.add(competitor);
            }
            return allCompetitors;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");//TODO: fix or delete
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Result> readResultFile(ArrayList<Competitor> competitors) {
        try {
            Scanner myReader = new Scanner(new File("data/results.csv"));
            ArrayList<Result> allResults = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Result result = new Result(data, competitors.get(0));
                allResults.add(result);
            }
            return allResults;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");//TODO: fix or delete
            e.printStackTrace();
        }
        return null;
    }

    public boolean authenticated(String name, String password, String role) {

        boolean isAuthenticated = false;

        File file = new File("data/users.csv");

        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] values = line.split(";");

                if (values[0].equals(name) && values[1].equals(password) && values[2].equals(role)) {
                    isAuthenticated = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }

}
