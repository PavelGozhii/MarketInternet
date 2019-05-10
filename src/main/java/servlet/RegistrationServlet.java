package servlet;

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

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(request.getParameter("login"), CodeGenerator.getSHA512SecurePsssword(request.getParameter("password")),
                request.getParameter("email"), request.getParameter("roleId"));
        if (userDao.insertUser(user)) {
            logger.debug("User is registered");
            request.setAttribute("login", user.getLogin());
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            logger.info("Forward to index.jsp");
            dispatcher.forward(request, response);
        } else {
            logger.warn("User already exists");
            request.setAttribute("ref", "index.jsp");
            request.setAttribute("error", "User already exists!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            logger.info("Forward to error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("RegistrationPage.jsp");
        logger.info("Forward to Registration.jsp");
        dispatcher.forward(request, response);
    }
}
