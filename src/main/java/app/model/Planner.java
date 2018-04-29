package app.model;

import java.util.ArrayList;
import java.util.*;



public class Planner {
    private int id;
    private String plannerName;
    private ArrayList<Milestone> milestone;
    private String cUser;
    private String shareId;


    private static int lastIdAllo = 0;

    public Planner(){
        this.shareId = "";
        this.id = 0 ;
        this.plannerName = "";
        this.milestone = new ArrayList<>();
    }

    public Planner(String plannerName){
        this.plannerName = plannerName;
        this.milestone = new ArrayList<>();
        this.shareId = shareId;
    }

    public Planner(int id, String plannerName){
        this.id = id;
        this.plannerName = plannerName;
        this.milestone = new ArrayList<>();
    }


    public Planner(String plannerName, String cUser, String shareId){
        this.id = id;
        this.plannerName = plannerName;
        this.milestone = new ArrayList<>();
        this.cUser = cUser;
        this.shareId = shareId;
    }

    public Planner(int id, String plannerName, String cUser){
        this.id = id;
        this.plannerName = plannerName;
        this.cUser = cUser;
        this.milestone = new ArrayList<>();

    }

    public Planner(int id, String plannerName, String cUser, String shareId){
        this.id = id;
        this.plannerName = plannerName;
        this.cUser = cUser;
        this.shareId = shareId;
    }

    public Planner(int id, String plannerName, ArrayList<Milestone> milestones, String cUser, String shareId){
        this.id = id;
        this.plannerName = plannerName;
        this.cUser = cUser;
        this.milestone = milestones;
        this.shareId = shareId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShareId(String shareId){this.shareId = shareId;}

    public String getPlannerName() {
        return plannerName;
    }

    public String getShareId(){
        return shareId;
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


    public String getcUser() {
        return cUser;
    }

    public void setcUser(String cUser) {
        this.cUser = cUser;
    }

    public String toString() {
        return "id:" + id + "name" + plannerName + "cUser" + cUser;
    }

}
