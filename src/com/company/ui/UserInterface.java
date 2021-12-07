package com.company.ui;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public void printWelcomeMessage(){
        //Found:
        //https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
        printMessage("""
                 __      __         .__                                         __                __   .__                 ________           .__           .__         \s
                /  \\    /  \\  ____  |  |    ____   ____    _____    ____      _/  |_  ____      _/  |_ |  |__    ____      \\______ \\    ____  |  |  ______  |__|  ____  \s
                \\   \\/\\/   /_/ __ \\ |  |  _/ ___\\ /  _ \\  /     \\ _/ __ \\     \\   __\\/  _ \\     \\   __\\|  |  \\ _/ __ \\      |    |  \\  /  _ \\ |  |  \\____ \\ |  | /    \\ \s
                 \\        / \\  ___/ |  |__\\  \\___(  <_> )|  Y Y  \\\\  ___/      |  | (  <_> )     |  |  |   Y  \\\\  ___/      |    `   \\(  <_> )|  |__|  |_> >|  ||   |  \\\s
                  \\__/\\  /   \\___  >|____/ \\___  >\\____/ |__|_|  / \\___  >     |__|  \\____/      |__|  |___|  / \\___  >    /_______  / \\____/ |____/|   __/ |__||___|  /\s
                       \\/        \\/            \\/              \\/      \\/                                   \\/      \\/             \\/               |__|             \\/ \s
                """);
    }

    public void startMenu(){
        printMessage("""
                Type (1) to Login.
                Type (2) to create a member.
                Type (3) to delete a member.
                Type (4) to get the list of members.
                Type (5) to check which members haven't paid their fees.
                Type (6) to see the swimmer's best results and dates.
                Type (7) to register an event, time and location for the swimmers that have participated.
                Type (8) to see the top 5 swimmers in each category.
                Type (0) to exit the program.
                """);
    }

    public void adminStartMenu(){
        printMessage("""
                Type (1) to create a member.
                Type (2) to delete a member.
                Type (3) to get the list of members.
                Type (0) to exit the program.
                """);
    }

    public void cashierStartMenu(){
        printMessage("""
                Type (1) to check which members haven't paid their fees.
                Type (0) to exit the program.
                """);
    }

    public void trainerStartMenu(){
        printMessage("""
                Type (1) to see the swimmer's best results and dates.
                Type (2) to register time and results for competitive swimmers.
                Type (3) to see the top 5 swimmers in each category.
                Type (0) to exit the program.
                """);
    }

    public void memberListMenu(){
        printMessage("""
                Which list would you like to view?
                (1) Full member list.
                (2) Junior member list.
                (3) Senior memberList.
                (4) Full competitorList.
                """);
    }

    public void disciplineMenu(){
        printMessage("""
                Choose which disciplines you want:
                (1) Butterfly.
                (2) Crawl.
                (3) Back crawl.
                (4) Breaststroke.
                """);
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printDouble(double number){
        System.out.println(number);
    }

    public void printInt(int number){
        System.out.println(number);
    }

    public void printError(){
        System.out.println("The input was invalid, please try again.");
    }

    public String stringInput(){
        return scanner.nextLine();
    }

    public int intInput(){
        int input = scanner.nextInt();
        scanner.nextLine(); //Scanner bug fix
        return input;
    }


    public LocalDate dateInput(){

        return LocalDate.parse(scanner.nextLine());
    }

    public Duration timeInput(){
        Duration input = Duration.ofSeconds(scanner.nextInt());
        return input;
    }

    public int validateInput(){
        int input = 0;
        boolean valid = true;
        while(valid) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                valid = false;
            } else {
                printMessage("Dette er ikke et nummer.");
                scanner.next();
            }
        }
        return input;
    }


}
