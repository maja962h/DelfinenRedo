package com.company.data;

import com.company.member.AgeRange;
import com.company.member.Discipline;
import com.company.member.Member;
import com.company.member.Result;
import com.company.ui.UserInterface;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;

public class ListController {

    private final UserInterface ui = new UserInterface();
    private final FileHandler fileHandler = new FileHandler();
    private final Database database = new Database();


    public void showMemberList() {
        ui.memberListMenu();
        int listInput = ui.intInput();

        switch (listInput) {
            case 1 -> fullMemberList();
            case 2 -> juniorMemberList();
            case 3 -> seniorMemberList();
            case 4 -> fullCompetitorList();
            default -> ui.printError();
        }
    }

    private void fullMemberList() {
        Collections.sort(fileHandler.getMemberList());

        ui.printMessage(fileHandler.makeStringMember());
    }

    private void fullCompetitorList() {
        Collections.sort(fileHandler.getMemberList());

        ui.printMessage(fileHandler.makeStringCompetitor());
    }

    private void juniorMemberList() {
        Collections.sort(fileHandler.getMemberList());

        for (Member member : fileHandler.getMemberList()) {
            if (member.getEnumAgeRange().equals(AgeRange.JUNIOR)) {
                ui.printMessage(member.toString());
            }
        }
    }

    public void seniorMemberList() {
        Collections.sort(fileHandler.getMemberList());

        for (Member member : fileHandler.getMemberList()) {
            if (member.getEnumAgeRange().equals(AgeRange.SENIOR)) {
                ui.printMessage(member.toString());
            }
        }
    }

    public void deleteMember(){
        int arrayCorrection = -1;
        ui.printMessage("Here is the list of all the members:");
        showMemberList();
        ui.printMessage("Please enter the number of the person you want to delete: ");
        int deleteMember = ui.intInput() + arrayCorrection;

        fileHandler.removeMember(deleteMember);
    }

    public void addCompetitorResults() {

        Discipline ds = null;
        ui.printMessage("Start by typing competitor name: ");
        String compName = ui.stringInput();

        ui.printMessage("Type training date (YYYY-MM-DD): ");
        LocalDate date = ui.dateInput();

        ui.printMessage("Next, type the swimmer's time (in seconds): ");
        Duration time = ui.timeInput();

        ui.printMessage("Lastly, chose the discipline: ");

        ui.disciplineMenu();
        int input = ui.intInput();
        if (input == 1) {
            ds = Discipline.BUTTERFLY;
        } else if (input == 2) {
            ds = Discipline.CRAWL;
        } else if (input == 3) {
            ds = Discipline.BACKCRAWL;
        } else if (input == 4) {
            ds = Discipline.BREASTSTROKE;
        }

        ui.printMessage("Is this a competition result? ('y' / 'n')");
        String answer = ui.stringInput();
        Result registeredResult = null;

        if(answer.equalsIgnoreCase("n")){
            registeredResult = new Result(database.findCompetitor(compName),date, ds, time);
        } else if(answer.equalsIgnoreCase("y")){
            ui.printMessage("Type in the name of the competition: ");
            String competitionName = ui.stringInput();
            registeredResult = new Result(database.findCompetitor(compName), date, ds, time, competitionName);
        }

        fileHandler.addNewResult(registeredResult);
        fileHandler.saveResults();

    }


}
