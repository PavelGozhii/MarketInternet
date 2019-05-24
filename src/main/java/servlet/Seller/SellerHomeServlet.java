package servlet.Seller;

import dao.GoodDao;
import dao.GoodDaoHibernate;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/seller/home", name = "SellerHome")
public class SellerHomeServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private static final Logger logger = Logger.getLogger(SellerHomeServlet.class);

    @Override
    public void init() throws ServletException {
        goodDao = new GoodDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        request.setAttribute("login", login);
        request.setAttribute("goodsList", goodDao.findByOwner(login));
        logger.info("Forward to SellerPage.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("SellerPage.jsp");
        dispatcher.forward(request, response);
    }
}
