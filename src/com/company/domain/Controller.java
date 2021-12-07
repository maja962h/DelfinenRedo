package com.company.domain;
import com.company.data.FileHandler;
import com.company.data.ListController;
import com.company.member.*;
import com.company.ui.UserInterface;


public class Controller {

    private final UserInterface ui = new UserInterface();
    private final FileHandler fileHandler = new FileHandler();
    private boolean running = true;
    private MemberController memberController = new MemberController();
    private ListController listController = new ListController();

    public void start() {

        ui.printWelcomeMessage();
        while (running) {
            ui.startMenu();
            int input = ui.validateInput();

            switch (input) {
                case 1 -> logIn();
                case 2 -> memberController.createMember();
                case 3 -> listController.deleteMember();
                case 4 -> listController.showMemberList();
                case 5 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 6 -> scoreBoard();
                case 7 -> listController.addCompetitorResults(); //place, time and registration for competitions.
                case 8 -> swimmerTierList(); //top 5 swimmers in every category.
                case 9 -> totalContingentAmount();
                case 10 -> System.out.println(fileHandler.getResults());
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

        boolean user = fileHandler.authenticated(name, password, role);
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
            int input = ui.validateInput();

            switch (input) {
                case 1 -> memberController.createMember();
                case 2 -> listController.deleteMember();
                case 3 -> listController.showMemberList();
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }

    public void cashierStart(){
        while (running) {
            ui.cashierStartMenu();
            int input = ui.validateInput();

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
            int input = ui.validateInput();

            switch (input) {
                case 1 -> scoreBoard();
                case 2 -> listController.addCompetitorResults(); //place, time and registration for competitions.
                case 3 -> swimmerTierList(); //top 5 swimmers in every category.
                case 0 -> exit();
                default -> ui.printError();
            }
        }
    }

    public void totalContingentAmount(){
        Calculator calc = new Calculator();
        calc.totalContingent(fileHandler);
        ui.printDouble(calc.getTotal());
    }


    public void checkDelinquentStatus() {

    }

    public void scoreBoard() {

    }


    public void swimmerTierList() {
    }

    public void exit() {
        running = false;
    }

}
