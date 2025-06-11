package marksapp.test;

import org.junit.Assert;
import org.junit.Test;

import marksapp.dao.UserDao;
import marksapp.model.UserData;

public class UserDaoTest {
    String correctName="Test Name";
    String correctEmail="test12345@gmail.com";
    String password="12345678";
    @Test
    public void registerWithNewCredentials(){
        UserData user= new UserData
        (correctName, correctEmail, password);
        UserDao dao= new UserDao();
        boolean result = dao.register(user);
        Assert.assertTrue
        ("Register should be successfull",result);
    }
}
