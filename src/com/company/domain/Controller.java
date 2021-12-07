package com.company.domain;
import com.company.data.Database;
import com.company.data.FileHandler;
import com.company.member.*;
import com.company.ui.UserInterface;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    private final UserInterface ui = new UserInterface();
    private final FileHandler fh = new FileHandler();
    private final Database db = new Database();
    private boolean running = true;
    private MemberController mc = new MemberController();

    public void start() {

        ui.printWelcomeMessage();
        while (running) {
            ui.startMenu();
            int input = ui.intInput();

            switch (input) {
                case 1 -> logIn();
                case 2 -> mc.createMember();
                case 3 -> deleteMember();
                case 4 -> showMemberList();
                case 5 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 6 -> scoreBoard();
                case 7 -> addCompetitorResults(); //place, time and registration for competitions.
                case 8 -> swimmerTierList(); //top 5 swimmers in every category.
                case 9 -> totalContingentAmount();
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }

    public void logIn() {
        ui.printMessage("Are you logging in as the admin, cashier or trainer?");
        String role = ui.stringInput();
        ui.printMessage("Type in user name: ");
        String name = ui.stringInput();
        ui.printMessage("Type in password: ");
        String password = ui.stringInput();

        boolean user = fh.authenticated(name, password, role);
        if(user){
            ui.printMessage("Welcome " + role + " " + name);
        } else {
            ui.printMessage("User not found");
        }

        switch (role) {
            case "admin" -> adminStart();
            case "cashier" -> cashierStart();
            case "trainer" -> trainerStart();
        }
    }

    public void adminStart(){
        while (running) {
            ui.adminStartMenu();
            int input = ui.intInput();

            switch (input) {
                case 1 -> mc.createMember();
                case 2 -> deleteMember();
                case 3 -> showMemberList();
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }

    public void cashierStart(){
        while (running) {
            ui.cashierStartMenu();
            int input = ui.intInput();

            switch (input) {
                case 1 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }

    public void trainerStart(){
        while (running) {
            ui.trainerStartMenu();
            int input = ui.intInput();

            switch (input) {
                case 1 -> scoreBoard();
                case 2 -> addCompetitorResults(); //place, time and registration for competitions.
                case 3 -> swimmerTierList(); //top 5 swimmers in every category.
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }


   /* public void createMember() {
        ui.printMessage("Please enter the members full name: ");
        String name = ui.stringInput();

        ui.printMessage("Please enter the members age: ");
        int age = ui.intInput();
        String ageRange = ageRange(age);

        ui.printMessage("Is the member active(a) or passive(p)?");
        String input = ui.stringInput();

        Member member = new Member(name, age, ageRange);
        isActiveOrPassive(member, input);

        fh.addNewMember(member);
        fh.saveMember();
    }*/

    public void deleteMember(){
        int arrayCorrection = -1;
        ui.printMessage("Here is the list of all the members:");
        showMemberList();
        ui.printMessage("Please enter the number of the person you want to delete: ");
        int deleteMember = ui.intInput() + arrayCorrection;

        fh.removeMember(deleteMember);
    }

  /*  public void isActiveOrPassive(Member member, String input){
        switch (input) {
            case "a" -> {
                member.setActiveStatus("active");
                ui.printMessage("Is the member an exerciser(e) or competitor(c)?");
                String eOrc = ui.stringInput();
                isCompetitorOrExerciser(member, eOrc);
            }
            case "p" -> {
                member.setActiveStatus("passive");
                member.setCompetitiveStatus("none");
            }
            default -> ui.printMessage("try again");
        }
    }

    public void isCompetitorOrExerciser(Member member, String input){
        switch (input) {
            case "c" -> {
                member.setCompetitiveStatus("Competitor");
                chooseDisciplines(member);
            }
            case "e" -> member.setCompetitiveStatus("Exerciser");
            default -> ui.printMessage("try again");
        }
    }

    public void chooseDisciplines(Member member) {
        boolean keepAdding;
        ArrayList<Discipline> selectedDiscipline = new ArrayList<>();

        do{
        ui.disciplineMenu();
        int choice = ui.intInput();
        selectedDiscipline = chooseDisciplines(choice, selectedDiscipline);
            ui.printMessage("do you want to add another discipline? yes(y) or no(n)");
            String addAnotherDiscipline = ui.stringInput();
            keepAdding = continueAddingDisciplines(addAnotherDiscipline);
        } while(keepAdding);

        Competitor competitor = new Competitor(member, selectedDiscipline);

        fh.addNewCompetitor(competitor);
        fh.saveCompetitor();

    }*/

    // You are able to view different member lists
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

    public void totalContingentAmount(){
        Calculator calc = new Calculator();
        calc.totalContingent();
        ui.printDouble(calc.getTotal());
    }


    public void checkDelinquentStatus() {

    }

    public void scoreBoard() {
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
                registeredResult = new Result(db.findCompetitor(compName),date, ds, time);
            } else if(answer.equalsIgnoreCase("y")){
                ui.printMessage("Type in the name of the competition: ");
                String competitionName = ui.stringInput();
                registeredResult = new Result(db.findCompetitor(compName), date, ds, time, competitionName);
            }

            fh.addNewResult(registeredResult);
            fh.saveResults();

    }

    public void swimmerTierList() {
    }

    public void exit() {
        running = false;
    }
/*
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
    }*/

    private void fullMemberList() {
        Collections.sort(fh.getMemberList());

        ui.printMessage(fh.makeStringMember());
    }

    private void fullCompetitorList() {
        Collections.sort(fh.getMemberList());

        ui.printMessage(fh.makeStringCompetitor());
    }

    private void juniorMemberList() {
        Collections.sort(fh.getMemberList());

        for (Member member : fh.getMemberList()) {
            if (member.getAgeRange().equals("Junior")) {
                ui.printMessage(member.toString());
            }
        }
    }

    public void seniorMemberList() {
        Collections.sort(fh.getMemberList());

        for (Member member : fh.getMemberList()) {
            if (member.getAgeRange().equals("Senior")) {
                ui.printMessage(member.toString());
            }
        }
    }

   /* public ArrayList<Discipline> chooseDisciplines(int input, ArrayList<Discipline> selectedDisciplines) {

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

    public boolean continueAddingDisciplines(String input) {
        if(input.equals("y")){
            return true;
        }else if(input.equals("n")){
            return false;
        }else
            return false;
    }*/




}
