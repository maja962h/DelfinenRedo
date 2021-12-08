package com.company.data;

import com.company.member.*;
import com.company.ui.UserInterface;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;

public class ListController {

    private final UserInterface userInterface = new UserInterface();
    private final FileHandler fileHandler = new FileHandler();
    private final DataHandler dataHandler = new DataHandler();


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

    //TODO: move to memberController


    //TODO: move to memberController
    public void removeMember(int member){
        fileHandler.getMemberList().remove(member);
        fileHandler.saveMember();
    }

    //TODO: move to memberController
    public void addNewResult(Result tr){
        fileHandler.getResults().add(tr);
    }

    private void fullMemberList() {
        Collections.sort(fileHandler.getMemberList());

        userInterface.printMessage(makeStringMember());
    }

    private void fullCompetitorList() {
        Collections.sort(fileHandler.getMemberList());

        userInterface.printMessage(makeStringCompetitor());
    }

    private void juniorMemberList() {
        Collections.sort(fileHandler.getMemberList());

        for (Member member : fileHandler.getMemberList()) {
            if (member.getEnumAgeRange().equals(AgeRange.JUNIOR)) {
                userInterface.printMessage(member.toString());
            }
        }
    }

    public void seniorMemberList() {
        Collections.sort(fileHandler.getMemberList());

        for (Member member : fileHandler.getMemberList()) {
            if (member.getEnumAgeRange().equals(AgeRange.SENIOR)) {
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
            registeredResult = new Result(dataHandler.findCompetitor(compName),date, ds, time);
        } else if(answer.equalsIgnoreCase("y")){
            userInterface.printMessage("Type in the name of the competition: ");
            String competitionName = userInterface.stringInput();
            registeredResult = new Result(dataHandler.findCompetitor(compName), date, ds, time, competitionName);
        }

        addNewResult(registeredResult);
        fileHandler.saveResults();

    }

    //TODO: move to memberController
    public String makeStringMember(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Member member : fileHandler.getMemberList()) {
            stringBuilder.append(member.toMemberString());
        }
        return stringBuilder.toString();
    }

    //TODO: move to memberController
    public String makeStringCompetitor(){

        StringBuilder stringBuilder = new StringBuilder();


        for (Competitor competitor : fileHandler.getCompetitors()) {
            stringBuilder.append(competitor.toCompString());
        }
        return stringBuilder.toString();
    }


}
