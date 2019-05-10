package servlet.User;

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

@WebServlet(value = "/user/home", name = "UserHome")
public class UserHomeServlet extends HttpServlet {
    private UserDao userDao;
    private GoodDao goodDao;
    private static final Logger logger = Logger.getLogger(UserHomeServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        goodDao = new GoodDao();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("goodsList", goodDao.selectAllGoods());
        request.setAttribute("login", request.getSession().getAttribute("login"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserPage.jsp");
        logger.info("Forward to UserPage.jsp");
        dispatcher.forward(request, response);
    }
}
