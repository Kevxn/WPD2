package db;
import app.model.*;

import db.H2Milestone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class dbtest {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(dbtest.class);

    private H2Milestone db;
    private H2Planner plannerDb;

    public dbtest() {
    }

    @Before
    public void setUp() {
        db = new H2Milestone();
        plannerDb = new H2Planner();
    }

    @After
    public void tearDown() {

            db.close();
    }



    @Test
    public void testAdd() {
     db.addMilestone(new Milestone("one", "one", 1, "01/01/2018"));
     db.addMilestone(new Milestone("two", "two", 2, "01/01/2018"));
     db.addMilestone(new Milestone("three", "three", 3, "01/01/2018"));
     db.addMilestone(new Milestone("four", "four", 4, "01/01/2018"));
     db.addMilestone(new Milestone("five", "five", 5, "01/01/2018"));
        List<Milestone> out = db.findMilestone();
        assertEquals(5, out.size());
    }

    @Test
    public void testReadTitle(){
        db.addMilestone(new Milestone("one", "one", 1, "01/01/2018"));
        List<Milestone> allMilestones = db.findMilestone();
        Milestone first = allMilestones.get(0);
        assertEquals("one", first.getTitle());
    }

    @Test
    public void testReadDescription(){
        db.addMilestone(new Milestone("two", "two description", 1, "01/01/2018"));
        List<Milestone> allMilestones = db.findMilestone();
        Milestone first = allMilestones.get(0);
        assertEquals("two description", first.getDescription());
    }

    @Test
    public void testReadMilestone3(){
        db.addMilestone(new Milestone("three", "three description", 1, "08/07/1998"));
        List<Milestone> allMilestones = db.findMilestone();
        Milestone first = allMilestones.get(0);
        assertEquals("08/07/1998", first.getDueDate());
    }

    @Test
    public void testCreatePlanner(){

        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu"));
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu"));
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu"));
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu"));
        List<Planner> plannerList = plannerDb.findPlanner();
        assertEquals(4, plannerList.size());

    }

    @Test
    public void testPlannerId(){
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu", "nope"));
        List<Planner> allPlanners = plannerDb.findPlanner();
        Planner current = allPlanners.get(0);
        assertEquals(1, current.getId());
    }

    @Test
    public void testPlannerName(){
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu", "nope"));
        List<Planner> allPlanners = plannerDb.findPlanner();
        Planner current = allPlanners.get(0);
        assertEquals("PlannerName", current.getPlannerName());
    }

    @Test
    public void testPlannerUser(){
        plannerDb.addPlanner(new Planner(1, "PlannerName", "gcu", "nope"));
        List<Planner> allPlanners = plannerDb.findPlanner();
        Planner current = allPlanners.get(0);
        assertEquals("gcu", current.getcUser());
    }



}
