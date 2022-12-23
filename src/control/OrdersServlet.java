package control;

import enity.Orders;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class OrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customer = request.getParameter("customer");
        BookService bookService = new BookServiceImpl();
        ArrayList<Orders> orders = bookService.getOrdersByCustomer(customer);
        HttpSession session = request.getSession();
        session.setAttribute("orders",orders);
        session.setAttribute("orders_num",orders.size());
        if ("true".equals((String)session.getAttribute("pass")))               // 重定向至普通用户的页面
            response.sendRedirect("show.jsp");
        else if("false".equals((String)session.getAttribute("pass")))          // 重定向至管理员的页面
            response.sendRedirect("admin_show.jsp");
        else
            response.getWriter().write("无法跳转未知信息...");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
