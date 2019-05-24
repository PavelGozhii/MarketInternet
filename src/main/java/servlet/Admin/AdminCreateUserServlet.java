package servlet.Admin;

import dao.UserDao;
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

@WebServlet(value = "/admin/create", name = "AdminCreate")
public class AdminCreateUserServlet extends HttpServlet {
    private UserDaoHibernate userDao;
    private Logger logger = Logger.getLogger(AdminCreateUserServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String roleId = request.getParameter("roleId");
        User user = new User(login, CodeGenerator.getSHA512SecurePsssword(password), email, roleId);
        logger.debug("Creating user" + login + " with role " + roleId);
        if (userDao.save(user)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/home");
            logger.info("Forward to /admin/home");
            dispatcher.forward(request, response);
        } else {
            logger.warn("Cannot create user");
            request.setAttribute("error", "Cannot create user");
            request.setAttribute("ref", "/admin/home");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forward to UserForm.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/UserForm.jsp");
        dispatcher.forward(request, response);
    }
}
