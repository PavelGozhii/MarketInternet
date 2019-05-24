package servlet.Seller;

import dao.GoodDao;
import dao.GoodDaoHibernate;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SellerEditServlet", value = "/seller/edit")
public class SellerEditServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private static final Logger logger = Logger.getLogger(SellerEditServlet.class);

    @Override
    public void init() throws ServletException {
        goodDao = new GoodDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String owner = (String) request.getSession().getAttribute("login");
        String id = request.getParameter("id");
        Good good = new Good(id, owner, name, description, price);
        logger.debug("Updating good");
        if (goodDao.update(good, id)) {
            logger.info("Forward to /seller/home");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/seller/home");
            dispatcher.forward(request, response);
        } else {
            logger.warn("Cannot update good");
            request.setAttribute("error", "Cannot update good");
            request.setAttribute("ref", "/seller/home");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("good", goodDao.findById(request.getParameter("id")));
        logger.info("Forward to GoodForm.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("GoodForm.jsp");
        dispatcher.forward(request, response);
    }
}
