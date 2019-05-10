package servlet;

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

@WebServlet(value = "/login", name = "Login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;
    private GoodDao goodDao;
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        goodDao = new GoodDao();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.selectUser(login);
        if (user == null) {
            logger.warn("UserNotFound");
            showErrorPage(req, resp, "UserNotFound");
        } else if (user.getHashPassword().equals(CodeGenerator.getSHA512SecurePsssword(password))) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", user.getRoleId());
            switch (user.getRoleId()) {
                case "admin":
                    logger.debug("Redirect to /admin/home");
                    resp.sendRedirect("/admin/home");
                    break;
                case "user":
                    logger.debug("Redirect to /user/home");
                    resp.sendRedirect("/user/home");
                    break;
                case "seller":
                    logger.debug("Redirect to /seller/home");
                    resp.sendRedirect("/seller/home");
                    break;
                default:
                    logger.warn("Unknown role");
                    showErrorPage(req, resp, "Unknown role");
            }
        } else {
            logger.warn("Incorrect password");
            showErrorPage(req, resp, "Incorrect password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }


    private void showErrorPage(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        request.setAttribute("ref", "index.jsp");
        request.setAttribute("error", error);
        logger.info("Forward to error.jsp");
        dispatcher.forward(request, response);
    }

}
