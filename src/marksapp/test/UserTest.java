package marksapp.test;

import org.junit.Assert;
import org.junit.Test;

import marksapp.dao.UserDao;
import marksapp.model.LoginRequest;
import marksapp.model.ResetPasswordRequest;
import marksapp.model.UserData;

public class UserTest {

    private UserDao dao = new UserDao();
    private String testName = "Test User";
    private String testEmail = "test@gmail.com";
    private String testPassword = "1234";

    @Test
    public void testRegisterUser() {
        UserData user = new UserData(testName, testEmail, testPassword);
        boolean result = dao.register(user);
        Assert.assertTrue("User registration should be successful", result);
    }

    @Test
    public void testRegisterDuplicateUser() {
        UserData user = new UserData(testName, testEmail, testPassword);
        boolean result = dao.register(user);
        Assert.assertFalse("Duplicate registration should fail", result);
    }

    @Test
    public void testLoginValidCredentials() {
        LoginRequest loginReq = new LoginRequest(testEmail, testPassword);
        UserData result = dao.login(loginReq);
        Assert.assertNotNull("Login should return a valid user", result);
        Assert.assertEquals("Email should match", testEmail, result.getEmail());
        Assert.assertEquals("Password should match", testPassword, result.getPassword());
        Assert.assertEquals("Name should match", testName, result.getName());
    }

    @Test
    public void testLoginInvalidCredentials() {
        LoginRequest loginReq = new LoginRequest(testEmail, "wrongpass");
        UserData result = dao.login(loginReq);
        Assert.assertNull("Login with invalid password should return null", result);
    }

    @Test
    public void testCheckEmailExists() {
        boolean exists = dao.checkEmail(testEmail);
        Assert.assertTrue("Email should exist", exists);
    }

    @Test
    public void testCheckEmailDoesNotExist() {
        boolean exists = dao.checkEmail("notexist@example.com");
        Assert.assertFalse("Email should not exist", exists);
    }

    @Test
    public void testResetPasswordSuccess() {
        ResetPasswordRequest reset = new ResetPasswordRequest(testEmail, "newpassword123");
        boolean result = dao.resetPassword(reset);
        Assert.assertTrue("Password reset should be successful", result);

        // Try to log in with new password
        LoginRequest loginReq = new LoginRequest(testEmail, "newpassword123");
        UserData user = dao.login(loginReq);
        Assert.assertNotNull("User should be able to login with new password", user);
    }

    @Test
    public void testResetPasswordFailureForNonExistingEmail() {
        ResetPasswordRequest reset = new ResetPasswordRequest("nonexisting@example.com", "somepass");
        boolean result = dao.resetPassword(reset);
        Assert.assertFalse("Password reset should fail for non-existing email", result);
    }
}
