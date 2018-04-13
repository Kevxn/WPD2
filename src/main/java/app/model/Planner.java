package app.model;

import java.util.ArrayList;
import java.util.*;



public class Planner {
   // private final int id;
    private String plannerName;
    private ArrayList<Milestone> milestone;


    private static int lastIdAllo = 0;

    public Planner(){
     //   this.id = ++lastIdAllo;
        this.plannerName = "";
        this.milestone = new ArrayList<>();
    }

    public Planner(String plannerName){
    //    this.id = ++lastIdAllo;
        this.plannerName = plannerName;
        this.milestone = new ArrayList<>();
    }

    public Planner(int plannerId, String plannerName, ArrayList<Milestone> milestone) {
    //    this.id = plannerId;
        this.plannerName = plannerName;
        this.milestone = milestone;
    }

    //public int getId() {
//
    ///    System.out.print(this.id);
    //    return this.id;

  //  }




    public String getPlannerName() {
        return plannerName;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
    }

    public void addMilestone(Milestone newMilestone){
        this.milestone.add(newMilestone);

    }

    public void addMilestones(List<Milestone> m){
        for (Milestone milestone : m){
           this.milestone.add(milestone);
        }

    }

    public void removeMilestone(int index) {
        this.milestone.remove(index);
    }

    public ArrayList<Milestone> getMilestone() {
        return milestone;
    }

    public void setMilestone(ArrayList<Milestone> milestone) {
        this.milestone = milestone;
    }




    public String toString() {
        return plannerName + "\n" +
                "\nMilestones: " + milestone.toString();
    }


}
