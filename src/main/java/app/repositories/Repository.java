package app.repositories;
import java.util.ArrayList;
import app.model.Planner;

public class Repository {

    private ArrayList<Planner> planner;

    public Repository() {
        this.planner = new ArrayList<>();
    }


    public ArrayList<Planner> getPlanner() {
        return planner;
    }

    public void setPlanner(ArrayList<Planner> planner) {
        this.planner = planner;
    }

    public void addPlanner(Planner planner) {
        this.planner.add(planner);

    }

    //  public void testMethod(){
    //     System.out.println("TEST");
    //     System.out.println(planner.size());
    //    for (Planner element : planner){
    //     System.out.print("test");
    //    System.out.println("Planner :" + element);}
    //   }


   // public Planner getPlannerById(int id) {
   //     for (Planner p : planner) {
    //        if (id == p.getId()) {
   //             return p;
    //        }
    //    }
   //     return null;

   // }

    public String toString() {
        return "\nPlanner: " + this.planner;
    }

}
