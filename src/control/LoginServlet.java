package control;

import enity.Book;
import enity.User;
import service.*;
import service.Impl.BookServiceImpl;
import service.Impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user = new User(username,pwd);
        UserService userService = new UserServiceImpl();
        User backuser = userService.checkUser(user);
        if(backuser != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("name",backuser.getUsername());

            if (backuser.getRoot() == 0)
            {
                session.setAttribute("pass","true");
                request.getRequestDispatcher("show").forward(request,response);
            }
            else
            {
                session.setAttribute("pass","false");
                response.sendRedirect("show");
            }
        }
        else
        {
            request.setAttribute("message","用户名或者密码输入错误，请重新输入");
            request.getRequestDispatcher("page.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
