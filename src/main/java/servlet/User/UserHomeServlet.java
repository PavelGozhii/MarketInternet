package servlet.User;

import dao.GoodDaoHibernate;
import dao.UserDaoHibernate;
import model.Good;
import model.Order;
import org.apache.log4j.Logger;
import service.CodeGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/home", name = "UserHome")
public class UserHomeServlet extends HttpServlet {
    private UserDaoHibernate userDao;
    private GoodDaoHibernate goodDao;
    private static final Logger logger = Logger.getLogger(UserHomeServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoHibernate();
        goodDao = new GoodDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("order") == null) {
            Order order = new Order();
            order.setId(String.valueOf(CodeGenerator.generateCode()));
            order.setIdUser((String) request.getSession().getAttribute("login"));
            request.getSession().setAttribute("order", order);
        }
        request.setAttribute("goodsList", goodDao.findAll(Good.class));
        request.setAttribute("login", request.getSession().getAttribute("login"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserPage.jsp");
        logger.info("Forward to UserPage.jsp");
        dispatcher.forward(request, response);
    }
}
