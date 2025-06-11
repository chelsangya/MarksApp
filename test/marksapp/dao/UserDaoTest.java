/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package marksapp.dao;

import marksapp.model.LoginRequest;
import marksapp.model.UserData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sangyakoirala
 */
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
    
    @Test
    public void registerWithExistingCredentials(){
        UserData user= new UserData
        (correctName,correctEmail,password);
        UserDao dao= new UserDao();
        boolean result = dao.register(user);
        Assert.assertFalse
        ("Register should fail for existing details",result);
    }
    @Test
    public void loginWithCorrectCreds(){
        LoginRequest req= new LoginRequest(correctEmail, password);
        UserDao dao = new UserDao();
        UserData user= dao.login(req);
        Assert.assertNotNull
        ("User should not be null",user);
        Assert.assertEquals
        ("Correct email should be retrieved",correctEmail, user.getEmail());
        Assert.assertEquals
        ("Correct name should be retrieved",correctName, user.getName());
    }

    
}
