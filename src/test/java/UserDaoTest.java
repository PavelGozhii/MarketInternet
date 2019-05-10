import dao.UserDao;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import service.CodeGenerator;

import java.util.List;

public class UserDaoTest extends Assert {

    @Rule
    public final Timeout timeout = new Timeout(1500);
    UserDao userDao = new UserDao();

    @Before
    public void beforeUserDaoTest() {
        User user = new User("testLogin", CodeGenerator.getSHA512SecurePsssword("12345"), "pavelgozhii@gmail.com", "user");
        userDao.insertUser(user);
    }

    @After
    public void afterUserDaoTest() {
        userDao.deleteUser("testLogin");
    }

    @Test
    public void insertUserDaoTest() {
        User newUser = userDao.selectUser("testLogin");
        assertEquals("testLogin", newUser.getLogin());
        assertEquals(CodeGenerator.getSHA512SecurePsssword("12345"), newUser.getHashPassword());
        assertEquals(null, userDao.selectUser("newTestLogin"));
    }

    @Test
    public void isExistUserDaoTest() {
        assertEquals(true, userDao.isExists("testLogin"));
        assertEquals(false, userDao.isExists("notExistsTestLogin"));
    }

    @Test
    public void selectAllUserDaoTest() {
        List<User> userList = userDao.selectAllUser();
        assertNotNull(userList);
    }

    @Test
    public void deleteUserDaoTest() {
        userDao.deleteUser("testLogin");
        assertEquals(false, userDao.isExists("testLogin"));
    }

    @Test
    public void updateUserDaoTest() {
        User user = new User("updateTestLogin", CodeGenerator.getSHA512SecurePsssword("54321"), "pavelgozhii@gmail.com", "user");
        assertEquals(true, userDao.updateUser(user, "testLogin"));
        User updateUser = userDao.selectUser("updateTestLogin");
        assertEquals(updateUser.getLogin(), "updateTestLogin");
        assertEquals(updateUser.getHashPassword(), CodeGenerator.getSHA512SecurePsssword("54321"));
        userDao.deleteUser("updateTestLogin");
    }
}