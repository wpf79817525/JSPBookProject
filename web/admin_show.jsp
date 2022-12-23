<%@ page import="service.BookService" %>
<%@ page import="service.Impl.BookServiceImpl" %>
<%@ page import="enity.Book" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 吴鹏飞
  Date: 2022/12/15
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    h1{
        padding-left: 650px
    }
    #index{
        padding-left: 100px
    }
    #bookTable{
        border: 1px;
        cellspacing: 0px;
        width: 400px;
        height: 300px;
    }
</style>

<html>
<head>
    <title>欢迎管理员光临</title>
</head>
<body>
    <div>
        <h1 style="padding-left: 650px">欢迎${sessionScope.name}光临...</h1><br>
        <div>
            <form action="ordershow">
                获取顾客已完成的订单信息:<input type="text" name="customer" placeholder="请输入顾客姓名"><br>
                <input type="submit" value="查看">
            </form>
        </div>

        <div>
            <c:if test="${sessionScope.orders_num == 0}">
                <h3>暂时没有该顾客的购买记录...</h3>
            </c:if>
            <c:if test="${sessionScope.orders_num > 0}">
                <table>
                    <tr>
                        <th>顾客</th>
                        <th>购买书名</th>
                        <th>购买数量</th>
                        <th>购买时间</th>
                    </tr>
                    <c:forEach var="i" items="${sessionScope.orders}">
                        <tr>
                            <td>${i.customer}</td>
                            <td>${i.buyName}</td>
                            <td>${i.buyNum}</td>
                            <td>${i.buyDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <h2><b>目前的书籍信息为:</b></h2>
        <table id="bookTable">
            <tr>
                <th>书名</th>
                <th>价格</th>
                <th>作者</th>
                <th>剩余数量</th>
            </tr>
            <c:forEach var="i" items="${sessionScope.books}">
                <tr>
                    <th>${i.name}</th>
                    <th>${i.price}</th>
                    <th>${i.writer}</th>
                    <th>${i.nums}</th>
                </tr>
            </c:forEach>
        </table>

        <a href="show?start=0" id="index">[首页]</a>
        <c:if test="${sessionScope.start > 0}">
            <a href="show?start=${pre}">[上一页]</a>
        </c:if>

        <c:if test="${sessionScope.size == sessionScope.count}">
            <a href="show?start=${next}">[下一页]</a>
        </c:if>
        <a href="show?start=${last}">[末页]</a>
    </div>

    <div>
        <form action="insert">
            请输入要添加的书名:<input type="text" name="insertName"><br>
            请输入书的价格:<input type="text" name="insertPrice"><br>
            请输入书的作者:<input type="text" name="insertWriter"><br>
            请输入要进货的数量:<input type="text" name="insertNum"><br>
            <input type="submit" value="确定">
        </form>
    </div>
    <b><a href="page.jsp">注销</a></b>
    <b><c:out value="${sessionScope.successInsert}" default=""></c:out></b>
</body>
</html>
