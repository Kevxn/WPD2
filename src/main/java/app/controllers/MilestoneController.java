package app.controllers;

import app.helpers.InputHelper;
import app.model.Milestone;
import app.model.Planner;
import app.repositories.Repository;

public class MilestoneController {
    static Repository repository;

    static {
        repository = new Repository();
    }

    public MilestoneController() {

    }

    public void run() {
        System.out.println("Welcome to the Milestone input for Planner one");
        boolean finished = false;

        do {
            char choice = displayMilestoneMenu();
            switch (choice) {
                case 'A':
                    addMile();
                    break;
                case 'B':
                    removeMilestone();
                    break;
                case 'C':
                    listMilestone();
                    break;
                case 'Q':
                    System.out.println("Returning to Planner selection...");
                    finished = true;
            }
        } while (!finished);
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }

    public char displayMilestoneMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Milestone");
        System.out.print("\nB. Remove Milestone");
        System.out.print("\nC. List Planners");
        System.out.print("\nQ. Quit\n");
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }

    private void addMile() {
        System.out.format("\033[31m%s\033[0m%n", "Add Milestone");
        InputHelper inputHelper = new InputHelper();
        Planner requiredPlanner;
        int id = 1;

        requiredPlanner = repository.getPlannerById(id);
        String title = inputHelper.readString("Enter Milestone Title");
        String desc = inputHelper.readString("Enter Milestone Text");
        Milestone newMilestone = new Milestone(title, desc);
        requiredPlanner.addMilestone(newMilestone);
    }

    private void removeMilestone() {
        System.out.format("\033[31m%s\033[0m%n", "Remove Milestone: ");
        System.out.format("\033[31m%s\033[0m%n", "===========");
        InputHelper inputHelper = new InputHelper();
        Planner requiredPlanner;
        int id = 1;
        requiredPlanner = repository.getPlannerById(id);
        System.out.println("Planner\n========\n" + requiredPlanner);
        int Index = inputHelper.readInt("Enter position of the Item to be removed");
        Index = Index - 1;
        requiredPlanner.removeMilestone(Index);
    }

    private void listMilestone() {
        System.out.format("\033[31m%s\033[0m%n", "Planners");
        System.out.println(repository.getPlanner());
    }



}
