package servlet.User;

import model.Order;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserBasketServlet", value = "/user/basket")
public class UserBasketServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserAddGoodToBasketServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = (Order) request.getSession().getAttribute("order");
        request.setAttribute("goods", order.getGoods().toArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("BasketPage.jsp");
        logger.debug("BasketPage.jsp");
        dispatcher.forward(request, response);
    }
}
