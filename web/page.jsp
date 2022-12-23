<%--
  Created by IntelliJ IDEA.
  User: 吴鹏飞
  Date: 2022/12/13
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea ;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login-title {
        margin: 0px auto 40px auto;
        text-align: center ;
        color: #505458;
    }
    .register-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 0px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
    }
    .input{
        width: 100%;
        height: 5%;
        border: none
    }
    #login{
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
    #register{
        width: 100%;
        height: 5%;
        background: #505458;
        border: none;
        color: white
    }
</style>

<html>
<head>
    <title>请登录</title>
</head>
<body>
    <c:remove var="orders" scope="session"></c:remove>
    <c:remove var="search_result" scope="session"></c:remove>
    <c:remove var="successBuy" scope="session"></c:remove>
    <c:remove var="successInsert" scope="session"></c:remove>

    <h2 style="padding-left: 625px;align-content: center"><c:out value="${requestScope.message}" default="请输入用户名以及密码"></c:out></h2>

    <form action="login" class="login-container">
        <h3 class="login-title">系统登录</h3>
        <input type="text" name="username" placeholder="用户名" class="input"><br>
        <input type="password" name="pwd" placeholder="密码" class="input"><br>
        <br>
        <input type="submit" value="登录" id="login"><br>
    </form>

    <div>
        <h3 align="center" style="">还没有账号?点击注册</h3>
        <form action="register.jsp" class="register-container">
            <input type="submit" value="注册" id="register"><br>
        </form>
    </div>
    <h3><b><c:out value="${sessionScope.successRegister}" default=""></c:out></b></h3>
</body>
</html>
