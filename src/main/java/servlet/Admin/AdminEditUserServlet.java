package servlet.Admin;

import dao.GoodDaoHibernate;
import dao.UserDaoHibernate;
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
    private UserDaoHibernate userDao;
    private GoodDaoHibernate goodDao;
    private static final Logger logger = Logger.getLogger(AdminEditUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoHibernate();
        goodDao = new GoodDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastLogin = request.getParameter("lastLogin");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String lastRoleId = request.getParameter("lastRoleId");
        String roleId = request.getParameter("roleId");
        User user = new User(login, CodeGenerator.getSHA512SecurePassword(password), email, roleId);
        if (lastRoleId.equals("seller") && !roleId.equals("seller")) {
            logger.debug("Deleting goods by owner");
            goodDao.deleteByOwner(login);
        }
        logger.debug("Updating user");
        userDao.update(user, lastLogin);
        logger.info("Forward to /admin/home");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/home");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", userDao.findById(User.class, request.getParameter("login")));
        logger.info("Forward to UserForm.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }
}
