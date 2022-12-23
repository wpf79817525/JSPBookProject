package control;

import enity.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String back = request.getParameter("back");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        if("".equals(username) || "".equals(pwd))
        {
            request.setAttribute("RegisterMessage","不允许用户名或者密码为空!!!");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
        else
        {
            User user = new User(username,pwd);
            UserService userService = new UserServiceImpl();
            User backuser = userService.registerUser(user);
            HttpSession session = request.getSession();
            if (backuser == null)
            {
                session.setAttribute("successRegister","注册已成功，请登录...");
                response.sendRedirect("page.jsp");
            }
            else
            {
                request.setAttribute("RegisterMessage","输入的用户名已经存在，请重新输入...");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
