package app.model;

import java.io.Serializable;
import java.util.Date;


public class Milestone implements Serializable {
    private int id;
    private String title;
    private String description;
    private int plannerId;
    private String dueDate;
    private String compDate;


    private static int lastIdAllo = 0;

    public Milestone(int id, String title, String description, int plannerId, String dueDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.plannerId = plannerId;
        this.dueDate = dueDate;
    }

    public Milestone(String title, String description, int plannerId){

        this.title = title;
        this.description = description;
        this.plannerId = plannerId;

    }



    public Milestone(String title, String description, int plannerId, String dueDate){
        this.id = ++lastIdAllo;
        this.title = title;
        this.description = description;
        this.plannerId = plannerId;
        this.dueDate = dueDate;
    }

    public Milestone(int id, String title, String description, int plannerId, String dueDate, String compDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.plannerId = plannerId;
        this.dueDate = dueDate;
        this.compDate = compDate;
    }


    public Milestone() {

        }

        public int getId () {
            return id;
        }

        public void setId ( int id){
            this.id = id;
        }

        public String getTitle () {
            return title;
        }

        public void setTitle (String title){
            this.title = title;
        }

        public String getDescription () {
            return description;
        }

        public void setDescription (String description){
            this.description = description;
        }

        public int getPlannerId () {
            return plannerId;
        }

        public String getDueDate () { return dueDate; }

    public String getCompDate() {
        return compDate;
    }

    public void setCompDate(String compDate) {
        this.compDate = compDate;
    }

    public String toString() {
        return "\nMilestone: " + title +
                "\nMilestone Description: " + description +
                "\n Milestone pid:" + plannerId;
    }

    }
