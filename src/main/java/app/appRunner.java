package app;

import app.controllers.PlannerController;
import app.repositories.Repository;



public class appRunner {

    static Repository repository = new Repository();

    public static void run() {
        System.out.println("Welcome\n");
        PlannerController plannerController = new PlannerController();
        plannerController.setRepository(repository);
        plannerController.run();

        System.out.println("Thank you for using.\n");
    }

    public static void main(String[] args) {
        appRunner runner = new appRunner();
        runner.run();
    }

}
