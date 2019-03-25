package seg.major.model;

import com.ja.security.PasswordHash;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seg.major.model.database.UserDAO;
import seg.major.structure.User;


public class LoginModelTest {
    @Before
    public void setUp() throws Exception {
        PasswordHash hash = new PasswordHash();
        UserDAO.create(new User(10000000,"hello12312313123","hel1312lo@he3231llo.he121llo",hash.createHash("world"),0));
    }

    @Test
    public void testValidateLogin() {
        User firstUser = UserDAO.getByUsername("hello12312313123");
        Assert.assertTrue(LoginModel.validateLogin(firstUser.getUsername(),"world"));
        Assert.assertFalse(LoginModel.validateLogin(firstUser.getUsername(),"world1"));
    }

    @After
    public void tearDown() {
        UserDAO.remove(UserDAO.getByUsername("hello12312313123"));
    }
}