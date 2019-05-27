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

@WebServlet(name = "SellerDeleteServlet", value = "/seller/delete")
public class SellerDeleteServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private static final Logger logger = Logger.getLogger(SellerDeleteServlet.class);

    @Override
    public void init() throws ServletException {
        goodDao = new GoodDaoHibernate();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.debug("Deleting good by Id");
        goodDao.delete(goodDao.findById(Good.class, id));
        logger.info("Forward to /seller/home");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/seller/home");
        dispatcher.forward(request, response);
    }
}
