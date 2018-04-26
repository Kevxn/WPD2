package app.model;

import java.util.Date;

public class Milestone {
    private String title;
    private String description;
    private int plannerId;

    public Milestone(String title, String description, int plannerId) {
        this.title = title;
        this.description = description;
        this.plannerId = plannerId;
    }



    //add new constructor here

    public Milestone(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(int plannerId) {
        this.plannerId = plannerId;
    }

    public String toString() {
        return "\nMilestone: " + title +
                "\nMilestone Description: " + description;
    }

}
