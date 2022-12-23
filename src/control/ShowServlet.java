package control;

import enity.Book;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        int count = 5;
        int start = 0;
        try {
            if (Integer.parseInt(request.getParameter("start")) >= 0)
                start = Integer.parseInt(request.getParameter("start"));          // 接收从show.jsp发来的start
        } catch (NumberFormatException e){}
        session.setAttribute("start",start);
        session.setAttribute("count",count);
        int next = start + count;
        int pre = start - count;
        session.setAttribute("next",next);
        session.setAttribute("pre",pre);
        ArrayList<Book> list = bookService.getAllBooks(start,count);
        ArrayList<Book> total_list = bookService.getAllBooks();
        int total_length = total_list.size();
        if(total_length % count == 0)
            session.setAttribute("last",total_length-count);
        else
            session.setAttribute("last",total_length-total_length%count);
        session.setAttribute("books",list);
        session.setAttribute("size",list.size());
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
