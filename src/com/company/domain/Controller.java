package com.company.domain;
import com.company.data.Database;
import com.company.data.FileHandler;
import com.company.data.ListController;
import com.company.member.*;
import com.company.ui.UserInterface;


public class Controller {

    private final UserInterface ui = new UserInterface();
    private final FileHandler fh = new FileHandler();
    private final Database db = new Database();
    private boolean running = true;
    private MemberController mc = new MemberController();
    private ListController lc = new ListController();

    public void start() {

        ui.printWelcomeMessage();
        while (running) {
            ui.startMenu();
            int input = ui.intInput();

            switch (input) {
                case 1 -> logIn();
                case 2 -> mc.createMember();
                case 3 -> lc.deleteMember();
                case 4 -> lc.showMemberList();
                case 5 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 6 -> scoreBoard();
                case 7 -> lc.addCompetitorResults(); //place, time and registration for competitions.
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
                case 2 -> lc.deleteMember();
                case 3 -> lc.showMemberList();
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
                case 2 -> lc.addCompetitorResults(); //place, time and registration for competitions.
                case 3 -> swimmerTierList(); //top 5 swimmers in every category.
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }


   /* public void deleteMember(){
        int arrayCorrection = -1;
        ui.printMessage("Here is the list of all the members:");
        showMemberList();
        ui.printMessage("Please enter the number of the person you want to delete: ");
        int deleteMember = ui.intInput() + arrayCorrection;

        fh.removeMember(deleteMember);
    }*/

    // You are able to view different member lists
    /*public void showMemberList() {
        ui.memberListMenu();
        int listInput = ui.intInput();

        switch (listInput) {
            case 1 -> fullMemberList();
            case 2 -> juniorMemberList();
            case 3 -> seniorMemberList();
            case 4 -> fullCompetitorList();
            default -> ui.printError();
        }
    }*/

    public void totalContingentAmount(){
        Calculator calc = new Calculator();
        calc.totalContingent();
        ui.printDouble(calc.getTotal());
    }


    public void checkDelinquentStatus() {

    }

    public void scoreBoard() {
    }

    /*public void addCompetitorResults() {

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

    }*/

    public void swimmerTierList() {
    }

    public void exit() {
        running = false;
    }

   /* private void fullMemberList() {
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
    }*/

}
