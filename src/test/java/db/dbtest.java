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

    public dbtest() {
    }

    @Before
    public void setUp() {
        db = new H2Milestone();
    }

    @After
    public void tearDown() {

            db.close();



    }

    @Test
    public void testAdd() {
     db.addMilestone(new Milestone("one", "one", 1));
     db.addMilestone(new Milestone("two", "two", 2));
     db.addMilestone(new Milestone("three", "three", 3));
     db.addMilestone(new Milestone("four", "four", 4));
     db.addMilestone(new Milestone("four", "four", 5));
        List<Milestone> out = db.findMilestone();
        assertEquals(5, out.size());
    }

}
