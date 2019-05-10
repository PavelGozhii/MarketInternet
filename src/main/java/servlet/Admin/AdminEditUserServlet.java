package servlet.Admin;

import dao.GoodDao;
import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import service.CodeGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/edit", name = "AdminEdit")
public class AdminEditUserServlet extends HttpServlet {
    private UserDao userDao;
    private GoodDao goodDao;
    private static final Logger logger = Logger.getLogger(AdminEditUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        goodDao = new GoodDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastLogin = request.getParameter("lastLogin");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String lastRoleId = request.getParameter("lastRoleId");
        String roleId = request.getParameter("roleId");
        User user = new User(login, CodeGenerator.getSHA512SecurePsssword(password), email, roleId);
        if (lastRoleId.equals("seller") && !roleId.equals("seller")) {
            logger.debug("Deleting goods by owner");
            goodDao.deleteGoodsByOwner(login);
        }
        logger.debug("Updating user");
        userDao.updateUser(user, lastLogin);
        logger.info("Forward to /admin/home");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/home");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", userDao.selectUser(request.getParameter("login")));
        logger.info("Forward to UserForm.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }
}
