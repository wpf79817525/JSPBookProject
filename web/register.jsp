<%--
  Created by IntelliJ IDEA.
  User: 吴鹏飞
  Date: 2022/12/15
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .register-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea ;
        box-shadow: 0 0 25px #cac6c6;
    }
    .register-title {
        margin: 0px auto 40px auto;
        text-align: center ;
        color: #505458;
    }
    .input{
        width: 100%;
        height: 5%;
        border: none
    }
    #register{
        width: 100%;
        height: 5%;
        background: #505458;
        border: none;
        color: white
    }
    a{
        align-content: center;
        padding-left: 120px;
        border: none
    }
</style>

<html>
<head>
    <title>注册页面</title>
</head>
<body>
    <h2 style="padding-left: 600px"><c:out value="${requestScope.RegisterMessage}" default="请输入注册的用户名以及密码"></c:out></h2>
    <form action="register" class="register-container">
        <h3 class="register-title">注册页面</h3>
        <input type="text" name="username"  placeholder="输入用户名" class="input"><br>
        <input type="password" name="pwd"  placeholder="输入密码" class="input"><br>
        <input type="submit" value="注册"  id="register">
        <a href="page.jsp">返回登录界面</a><br>
    </form>
</body>
</html>
