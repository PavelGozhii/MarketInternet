package servlet.Admin;

import dao.GoodDao;
import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/delete", name = "AdminDelete")
public class AdminDeleteUserServlet extends HttpServlet {
    private UserDao userDao;
    private GoodDao goodDao;
    private static final Logger logger = Logger.getLogger(AdminDeleteUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        goodDao = new GoodDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String roleId = userDao.selectUser(login).getRoleId();
        if (roleId.equals("seller")) {
            logger.debug("Deleting goods by owner");
            goodDao.deleteGoodsByOwner(login);
        }
        logger.debug("Deleting user");
        userDao.deleteUser(login);
        logger.info("Forward to /admin/home");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/home");
        dispatcher.forward(request, response);
    }
}
