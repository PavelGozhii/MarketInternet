package servlet.Seller;

import dao.GoodDao;
import dao.GoodDaoHibernate;
import model.Good;
import org.apache.log4j.Logger;
import service.CodeGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SellerCreate", value = "/seller/create")
public class SellerCreateServlet extends HttpServlet {
    private GoodDaoHibernate goodDao;
    private Logger logger = Logger.getLogger(SellerCreateServlet.class);

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
        String id;
        do {
            id = CodeGenerator.generateId(name);
        } while (goodDao.findById(id) != null);
        Good good = new Good(id, owner, name, description, price);
        logger.debug("Add new goods");
        if (goodDao.saveGood(good)) {
            logger.info("Forward to /seller/home");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/seller/home");
            dispatcher.forward(request, response);
        } else {
            logger.warn("Cannot add good");
            request.setAttribute("error", "Cannot add good");
            request.setAttribute("ref", "/seller/home");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forward to /seller/GoodForm.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/seller/GoodForm.jsp");
        dispatcher.forward(request, response);
    }
}
