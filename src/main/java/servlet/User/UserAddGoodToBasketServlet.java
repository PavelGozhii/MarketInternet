package servlet.User;

import dao.GoodDaoHibernate;
import dao.UserDaoHibernate;
import model.Good;
import model.Order;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserAddGoodToBasketServlet", value = "/user/add")
public class UserAddGoodToBasketServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private UserDaoHibernate userDao;
    private static final Logger logger = Logger.getLogger(UserAddGoodToBasketServlet.class);

    @Override
    public void init() throws ServletException {
        goodDao = new GoodDaoHibernate();
        userDao = new UserDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Add to cart");
        Order order = (Order) request.getSession().getAttribute("order");
        String id = request.getParameter("id");
        Good good = goodDao.findById(Good.class, id);
        order.getGoods().add(good);
        request.getSession().setAttribute("order", order);
        request.setAttribute("goodsList", goodDao.findAll(Good.class));
        request.setAttribute("login", request.getSession().getAttribute("login"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserPage.jsp");
        logger.debug("Forward to UserPage.jsp");
        dispatcher.forward(request, response);
    }
}
