package com.company.member;

import com.company.data.FileHandler;
import com.company.ui.UserInterface;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class MemberController {

    private UserInterface userInterface = new UserInterface();
    private FileHandler fileHandler = new FileHandler();
    private ArrayList<Member> memberList;
    private ArrayList<Competitor> competitors;
    private ArrayList<Result> results;


    public MemberController(){
        receiveAllMembers();
        receiveAllCompetitors();
        receiveAllResults();
    }

    public void receiveAllCompetitors(){
        competitors = fileHandler.readCompetitorFile();
    }

    public void receiveAllResults(){
        results = fileHandler.readResultFile(competitors);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public Competitor findCompetitor(String name){
        for (Competitor competitor: competitors) {
            if(competitor.getName().equalsIgnoreCase(name.trim())){
                return competitor;
            }
        }
        return null;
    }


    public void createMember() {
        userInterface.printMessage("Please enter the members full name: ");
        String name = userInterface.stringInput();

        userInterface.printMessage("Please enter the members age: ");
        int age = userInterface.intInput();
        String ageRange = ageRange(age);

        userInterface.printMessage("Is the member active(a) or passive(p)?");
        String input = userInterface.stringInput();

        Member member = new Member(name, age, ageRange);
        isActiveOrPassive(member, input);

        addNewMember(member);
        fileHandler.saveMember(memberList);
    }

    public void isActiveOrPassive(Member member, String input){
        switch (input) {
            case "a" -> {
                member.setActiveStatus("active");
                userInterface.printMessage("Is the member an exerciser(e) or competitor(c)?");
                String eOrc = userInterface.stringInput();
                isCompetitorOrExerciser(member, eOrc);
            }
            case "p" -> {
                member.setActiveStatus("passive");
                member.setCompetitiveStatus("none");
            }
            default -> userInterface.printMessage("try again");
        }
    }

    public void isCompetitorOrExerciser(Member member, String input){
        switch (input) {
            case "c" -> {
                member.setCompetitiveStatus("Competitor");
                chooseDisciplines(member);
            }
            case "e" -> member.setCompetitiveStatus("Exerciser");
            default -> userInterface.printMessage("try again");
        }
    }

    public void showMemberList() {
        userInterface.memberListMenu();
        int listInput = userInterface.intInput();

        switch (listInput) {
            case 1 -> fullMemberList();
            case 2 -> juniorMemberList();
            case 3 -> seniorMemberList();
            case 4 -> fullCompetitorList();
            default -> userInterface.printError();
        }
    }

    private void fullMemberList() {
        Collections.sort(getMemberList());

        userInterface.printMessage(makeStringMember());
    }



    private void fullCompetitorList() {
        Collections.sort(getMemberList());

        userInterface.printMessage(makeStringCompetitor());
    }

    private void juniorMemberList() {
        Collections.sort(getMemberList());

        for (Member member : getMemberList()) {
            if (member.getAgeRange().equals("JUNIOR")) {
                userInterface.printMessage(member.toString());
            }
        }
    }

    public String makeStringMember(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Member member : getMemberList()) {
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

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void receiveAllMembers(){
        memberList = fileHandler.readFile();
    }


    public void seniorMemberList() {
        Collections.sort(getMemberList());

        for (Member member : getMemberList()) {
            if (member.getAgeRange().equals("SENIOR")) {
                userInterface.printMessage(member.toString());
            }
        }
    }

    public void deleteMember(){
        int arrayCorrection = -1;
        userInterface.printMessage("Here is the list of all the members:");
        showMemberList();
        userInterface.printMessage("Please enter the number of the person you want to delete: ");
        int deleteMember = userInterface.intInput() + arrayCorrection;

        removeMember(deleteMember);
    }

    public Member findMember(String name){
        for (Member member: getMemberList()) {
            if(member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    //TODO: move to memberController
    public void removeMember(int member){
        getMemberList().remove(member);
        fileHandler.saveMember(memberList);
    }

    public void chooseDisciplines(Member member) {
        boolean keepAdding;
        ArrayList<Discipline> selectedDiscipline = new ArrayList<>();

        do{
            userInterface.disciplineMenu();
            int choice = userInterface.intInput();
            selectedDiscipline = chooseDisciplines(choice, selectedDiscipline);
            userInterface.printMessage("do you want to add another discipline? yes(y) or no(n)");
            String addAnotherDiscipline = userInterface.stringInput();
            keepAdding = continueAddingDisciplines(addAnotherDiscipline);
        } while(keepAdding);

        Competitor competitor = new Competitor(member, selectedDiscipline);

        addNewCompetitor(competitor);
        fileHandler.saveCompetitor(competitors);
    }

    public void addNewMember(Member member){
        memberList.add(member);
    }

    public void addNewCompetitor(Competitor competitor){
        competitors.add(competitor);
    }


    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public boolean continueAddingDisciplines(String input) {
        if(input.equals("y")){
            return true;
        }else if(input.equals("n")){
            return false;
        }else
            return false;
    }

    public ArrayList<Discipline> chooseDisciplines(int input, ArrayList<Discipline> selectedDisciplines) {

        if (input == 1) {
            selectedDisciplines.add(Discipline.BUTTERFLY);
        } else if (input == 2) {
            selectedDisciplines.add(Discipline.CRAWL);
        } else if (input == 3) {
            selectedDisciplines.add(Discipline.BACKCRAWL);
        } else if (input == 4) {
            selectedDisciplines.add(Discipline.BREASTSTROKE);
        }
        return selectedDisciplines;
    }

    private String ageRange(int age) {
        String ageRange = "";
        if (age < 18) {
            ageRange = "Junior";
        } else if (age >= 18 && age < 60) {
            ageRange = "Senior";
        } else if(age >= 60){
            ageRange = "elder";
        }
        return ageRange;
    }

    //TODO: move to memberController
    public void addNewResult(Result tr){
        results.add(tr);
    }

    public void addCompetitorResults() {

        Discipline ds = null;
        userInterface.printMessage("Start by typing competitor name: ");
        String compName = userInterface.stringInput();

        userInterface.printMessage("Type training date (YYYY-MM-DD): ");
        LocalDate date = userInterface.dateInput();

        userInterface.printMessage("Next, type the swimmer's time (in seconds): ");
        Duration time = userInterface.timeInput();

        userInterface.printMessage("Lastly, chose the discipline: ");

        userInterface.disciplineMenu();
        int input = userInterface.intInput();
        if (input == 1) {
            ds = Discipline.BUTTERFLY;
        } else if (input == 2) {
            ds = Discipline.CRAWL;
        } else if (input == 3) {
            ds = Discipline.BACKCRAWL;
        } else if (input == 4) {
            ds = Discipline.BREASTSTROKE;
        }

        userInterface.printMessage("Is this a competition result? ('y' / 'n')");
        String answer = userInterface.stringInput();
        Result registeredResult = null;

        if(answer.equalsIgnoreCase("n")){
            registeredResult = new Result(findCompetitor(compName),date, ds, time);
        } else if(answer.equalsIgnoreCase("y")){
            userInterface.printMessage("Type in the name of the competition: ");
            String competitionName = userInterface.stringInput();
            registeredResult = new Result(findCompetitor(compName), date, ds, time, competitionName);
        }

        addNewResult(registeredResult);
        fileHandler.saveResults(results);

    }

}
