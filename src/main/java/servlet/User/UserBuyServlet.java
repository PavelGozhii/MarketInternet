package servlet.User;

import dao.GoodDaoHibernate;
import dao.OrderDaoHibernate;
import dao.UserDaoHibernate;
import model.Good;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import service.CodeGenerator;
import service.EmailSender;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserBuyServlet", value = "/user/buy")
public class UserBuyServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private UserDaoHibernate userDao;
    private OrderDaoHibernate orderDao;
    private static final Logger logger = Logger.getLogger(UserBuyServlet.class);

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoHibernate();
        goodDao = new GoodDaoHibernate();
        orderDao = new OrderDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code.equals(request.getSession().getAttribute("code"))) {
            logger.debug("Purchase success");
            request.getSession().removeAttribute("code");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
            Order order = (Order) request.getSession().getAttribute("order");
            orderDao.save(order);
            System.out.println(order.getId());
            request.setAttribute("message", "Purchase successful");
            request.setAttribute("ref", "/user/home");
            logger.info("Forward to /success.jsp");
            dispatcher.forward(request, response);
        } else {
            logger.warn("Incorrect purchase code");
            request.getSession().removeAttribute("code");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("error", "Incorrect code");
            request.setAttribute("ref", "/user/home");
            logger.info("Forward to error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userDao.findById(User.class, (String) request.getSession().getAttribute("login"));
        String email = user.getEmail();
        int code = CodeGenerator.generateCode();
        String codeString = String.valueOf(code);
        request.getSession().setAttribute("code", codeString);
        EmailSender.sendMessage(email, "Your code: " + code);
        Order order = (Order) request.getSession().getAttribute("order");
        request.setAttribute("goods", order.getGoods().toArray());
        logger.info("Forward to BuyPage.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("BuyPage.jsp");
        dispatcher.forward(request, response);
    }
}
