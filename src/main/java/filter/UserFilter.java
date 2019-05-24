package filter;

import dao.UserDao;
import dao.UserDaoHibernate;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/user/*")
public class UserFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AdminFilter.class);
    private UserDaoHibernate userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new UserDaoHibernate();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String role = (String) request.getSession().getAttribute("role");
        String login = (String) request.getSession().getAttribute("login");
        if (role.equals("user")) {
            logger.info("UserAccess Granted to" + login);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.warn("UserAccess Denied to" + login);
            request.setAttribute("error", "Access Denied");
            request.setAttribute("ref", "/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
