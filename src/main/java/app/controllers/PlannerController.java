package app.controllers;

import app.helpers.InputHelper;
import app.model.Milestone;
import app.model.Planner;
import app.repositories.Repository;
import app.controllers.MilestoneController;



public class PlannerController {

    static Repository repository = new Repository();


    public void run() {
        boolean finished = false;
        do {
            char choice = displayStartMenu();
            switch (choice) {
                case 'A':
                    addPlanner();
                    break;
                case 'B':
                    MilestoneController m = new MilestoneController();
                    m.setRepository(repository);
                    //moves to milestone controller
                    m.run();
                case 'D':
                    listPlanners();
                    break;
                case 'Q':
                    finished = true;
            }
        } while (!finished);
    }


    public void setRepository(Repository repository){
        this.repository = repository;
    }

    private char displayStartMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Planner");
        System.out.print("\nB. Milestone Control");
        System.out.print("\nD. List Planners");
        System.out.print("\nQ. Quit\n");
        return inputHelper.readCharacter("Enter choice", "ABCDEFQ");
    }

    private void addPlanner() {
        System.out.format("\033[31m%s\033[0m%n", "Add Planner");
        InputHelper inputHelper = new InputHelper();
        String newNoteHeading = inputHelper.readString("Enter Planner Heading");
        Planner planner = new Planner(newNoteHeading);
        repository.addPlanner(planner);
    }

    private void listPlanners() {
        System.out.format("\033[31m%s\033[0m%n", "Planners");
        System.out.println(repository);
    }




}




