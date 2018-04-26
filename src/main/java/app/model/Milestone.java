package app.model;

import java.util.Date;


public class Milestone {
    private int id;
    private String title;
    private String description;
    private int plannerId;


    private static int lastIdAllo = 0;

    public Milestone(int id, String title, String description, int plannerId){
            this.id = id;
            this.title = title;
            this.description = description;
            this.plannerId = plannerId;
        }

//^ above is used with db

    public Milestone(String title, String description, int plannerId){
            this.id = ++lastIdAllo;
            this.title = title;
            this.description = description;
            this.plannerId = plannerId;
        }


        //add new constructor here

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

        public void setPlannerId ( int plannerId){
            this.plannerId = plannerId;
        }

        public String toString () {
            return "\nMilestone: " + title +
                    "\nMilestone Description: " + description;
        }

    }
