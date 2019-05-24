package servlet.Admin;

import dao.UserDao;
import dao.UserDaoHibernate;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/home", name = "AdminHome")
public class AdminHomeServlet extends HttpServlet {
    private UserDaoHibernate userDao;
    private static final Logger logger = Logger.getLogger(AdminHomeServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userList", userDao.findAll());
        logger.info("Forwarding to AdminPage.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");
        dispatcher.forward(request, response);
    }
}
