package marksapp.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import marksapp.dao.MarksDao;
import marksapp.model.MarksData;

public class MarksDaoTest {
    @Test
    public void testAddMarks() {
        MarksDao dao = new MarksDao();
        MarksData marks = new MarksData(32, "Test User", 1, 85, 90, 95);
        boolean result = dao.addMarks(marks);
        Assert.assertTrue("Adding marks should be successful", result);

    }

    @Test
    public void testGetMarks() {
        MarksDao dao = new MarksDao();
        ArrayList<MarksData> marks = dao.getMarksForUid(32);
        Assert.assertNotNull("Marks should not be null", marks);
        Assert.assertFalse("Marks list should not be empty", marks.isEmpty());
        for (MarksData mark : marks) {
            System.out.println("marks id" + mark.getId());
            Assert.assertEquals("User ID should match", 32, mark.getUserId());
        }

    }

    @Test
    public void testUpdateMarks() {
        MarksDao dao = new MarksDao();
        MarksData marks = new MarksData(32, 7, "Test User", 1, 85, 90, 95);
        boolean result = dao.updateMarks(marks);
        Assert.assertTrue("Updating marks should be successful", result);
    }

    @Test
    public void testDeleteMarks() {
        MarksDao dao = new MarksDao();
        boolean result = dao.deleteMarks(4, 32);
        Assert.assertTrue("Deleting marks should be successful", result);
        // Verify the deletion
        ArrayList<MarksData> marks = dao.getMarksForUid(1);
        Assert.assertTrue("Marks list should be empty after deletion", marks.isEmpty());
    }

}
