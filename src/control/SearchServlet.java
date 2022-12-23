package control;

import enity.Book;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String writer = request.getParameter("writer");
        System.out.println(writer);
        BookService bookService = new BookServiceImpl();
        ArrayList<Book> lst = bookService.getBooksByWriter(writer);
        HttpSession session = request.getSession();
        session.setAttribute("search_result",lst);
        session.setAttribute("search_result_nums",lst.size());
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
