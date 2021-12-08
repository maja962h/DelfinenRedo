package com.company.member;

import com.company.data.FileHandler;
import com.company.ui.UserInterface;

import java.util.ArrayList;

public class MemberController {

    private final UserInterface userInterface = new UserInterface();
    private final FileHandler fileHandler = new FileHandler();


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

        fileHandler.addNewMember(member);
        fileHandler.saveMember();
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

        fileHandler.addNewCompetitor(competitor);
        fileHandler.saveCompetitor();

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

}
