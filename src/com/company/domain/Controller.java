package com.company.domain;
import com.company.data.FileHandler;
import com.company.member.*;
import com.company.ui.UserInterface;


public class Controller {

    private final UserInterface userInterface = new UserInterface();
    private final FileHandler fileHandler = new FileHandler();
    private boolean running = true;
    private MemberController memberController = new MemberController();

    public void start() {

        userInterface.printWelcomeMessage();
        while (running) {
            userInterface.startMenu();
            int input = userInterface.validateInput();

            switch (input) {
                case 1 -> logIn();
                case 2 -> memberController.createMember();
                case 3 -> memberController.deleteMember();
                case 4 -> memberController.showMemberList();
                case 5 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 6 -> scoreBoard();
                case 7 -> memberController.addCompetitorResults(); //place, time and registration for competitions.
                case 8 -> swimmerTierList(); //top 5 swimmers in every category.
                case 9 -> totalContingentAmount();
                case 10 -> System.out.println(fileHandler.getResults());
                case 0 -> exit();
                default -> userInterface.printError();
            }
        }
    }

    public void logIn() {
        userInterface.printMessage("Are you logging in as the admin, cashier or trainer?");
        String role = userInterface.stringInput();
        userInterface.printMessage("Type in user name: ");
        String name = userInterface.stringInput();
        userInterface.printMessage("Type in password: ");
        String password = userInterface.stringInput();

        boolean user = fileHandler.authenticated(name, password, role);
        if(user){
            userInterface.printMessage("Welcome " + role + " " + name);
        } else {
            userInterface.printMessage("User not found");
        }

        switch (role) {
            case "admin" -> adminStart();
            case "cashier" -> cashierStart();
            case "trainer" -> trainerStart();
        }
    }

    public void adminStart(){
        while (running) {
            userInterface.adminStartMenu();
            int input = userInterface.validateInput();

            switch (input) {
                case 1 -> memberController.createMember();
                case 2 -> memberController.deleteMember();
                case 3 -> memberController.showMemberList();
                case 0 -> exit();
                default -> userInterface.printError();
            }
        }
    }

    public void cashierStart(){
        while (running) {
            userInterface.cashierStartMenu();
            int input = userInterface.validateInput();

            switch (input) {
                case 1 -> checkDelinquentStatus(); //Check what members have not paid their fees.
                case 0 -> exit();
                default -> userInterface.printError();
            }
        }
    }

    public void trainerStart(){
        while (running) {
            userInterface.trainerStartMenu();
            int input = userInterface.validateInput();

            switch (input) {
                case 1 -> scoreBoard();
                case 2 -> memberController.addCompetitorResults(); //place, time and registration for competitions.
                case 3 -> swimmerTierList(); //top 5 swimmers in every category.
                case 0 -> exit();
                default -> userInterface.printError();
            }
        }
    }

    public void totalContingentAmount(){
        Calculator calculator = new Calculator();
        calculator.totalContingent(memberController.getMemberList());
        userInterface.printDouble(calculator.getTotal());
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
