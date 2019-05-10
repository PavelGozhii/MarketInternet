import dao.GoodDao;
import model.Good;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class GoodDaoTest extends Assert {

    @Rule
    public final Timeout timeout = new Timeout(1000);
    GoodDao goodDao = new GoodDao();

    @Before
    public void beforeGoodDaoTest() {
        Good good = new Good("1", "PavelGo", "Bread", "Bread", 100);
        goodDao.insertGood(good);
    }

    @After
    public void afterGoodDaoTest() {
        goodDao.deleteGoodsById("1");
    }

    @Test
    public void selectGoodDaoTest() {
        Good newGood = goodDao.selectGood("1");
        assertEquals("Bread", newGood.getName());
        assertEquals(null, goodDao.selectGood("12345"));
    }

    @Test
    public void insertGoodDaoTest() {
        assertEquals(null, goodDao.selectGood("2"));
        goodDao.insertGood(new Good("2", "PavelGo", "Bread", "Bread", 100));
        assertEquals("Bread", goodDao.selectGood("2").getName());
        goodDao.deleteGoodsById("2");
    }

    @Test
    public void deleteGoodDaoTest() {
        goodDao.deleteGoodsById("1");
        assertEquals(null, goodDao.selectGood("1"));
    }

    @Test
    public void updateGoodDaoTest() {
        Good good = new Good("1", "PavelGo", "Meat", "Meat", 200);
        goodDao.updateGood(good, "1");
        assertEquals("Meat", goodDao.selectGood("1").getName());
        assertNotEquals("Bread", goodDao.selectGood("1"));
    }

}
