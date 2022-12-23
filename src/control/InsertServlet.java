package control;

import enity.Book;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String insertName = request.getParameter("insertName");
        double insertPrice = 0;
        int insertNum = 0;
        String insertWriter = request.getParameter("insertWriter");
        if (!"".equals((String)request.getParameter("insertPrice")))
            insertPrice = Double.parseDouble((String)request.getParameter("insertPrice"));
        if (!"".equals((String)request.getParameter("insertNum")))
            insertNum = Integer.parseInt((String)request.getParameter("insertNum"));
        Book insertBook = new Book(insertName,insertPrice,insertWriter,insertNum);
        BookService bookService = new BookServiceImpl();
        Book backbook = bookService.insertBook(insertBook);
        HttpSession session = request.getSession();
        if (backbook != null)
        {
            session.setAttribute("successInsert","添加成功!!!");
            response.sendRedirect("show");
        }
        else
        {
            session.setAttribute("successInsert","添加失败...");
            request.getRequestDispatcher("show").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
