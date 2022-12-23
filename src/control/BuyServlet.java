package control;

import enity.Orders;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buyName = request.getParameter("buyName");
        HttpSession session = request.getSession();
        int buyNum = 0;
        if (!((String)request.getParameter("buyNum")).equals(""))
            buyNum = Integer.parseInt(request.getParameter("buyNum"));
        String username = (String) session.getAttribute("name");
        Date buyDate = new Date(System.currentTimeMillis());         // 获取时间(其实不太合理，应该是根据浏览器端的时间进行插入)
        Orders order = new Orders(username,buyName,buyNum,buyDate);
        BookService bookService = new BookServiceImpl();

        if (buyNum <= 0)
        {
            session.setAttribute("successBuy","请输入正确的数...");
            response.sendRedirect("show.jsp");
        }
        else
        {
            boolean successBuy = bookService.buyBooks(order);
            if (successBuy)
            {
                session.setAttribute("successBuy","购买成功!!!");
                request.getRequestDispatcher("show").forward(request,response);
            }
            else
            {
                session.setAttribute("successBuy","您输入的数量过多，购买失败，请重新购买");
                response.sendRedirect("show");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
