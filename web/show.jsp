<%@ page import="service.BookService" %>
<%@ page import="service.Impl.BookServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="enity.Book" %><%--
  Created by IntelliJ IDEA.
  User: 吴鹏飞
  Date: 2022/12/13
  Time: 17:57
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
    <title>欢迎光临</title>
</head>
<body>
    <div>
        <h1>欢迎${sessionScope.name}光临...</h1><br>
        <div>
            <form action="search">
                获取相关作者的书:<input type="text" name="writer" placeholder="请输入作者姓名"><br>
                <input type="submit" value="搜索">
            </form>
        </div>

        <div>
            <c:if test="${sessionScope.search_result_nums == 0}">
                <b>没有查到相关作者的书...</b>
            </c:if>
            <c:if test="${sessionScope.search_result_nums > 0}">
                <table>
                    <tr>
                        <th>书名</th>
                        <th>价格</th>
                        <th>作者</th>
                        <th>剩余数量</th>
                    </tr>
                    <c:forEach var="i" items="${sessionScope.search_result}">
                        <tr>
                            <td>${i.name}</td>
                            <td>${i.price}</td>
                            <td>${i.writer}</td>
                            <td>${i.nums}</td>
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
            <a href="show?start=${sessionScope.pre}">[上一页]</a>
        </c:if>

        <c:if test="${sessionScope.size == sessionScope.count}">
            <a href="show?start=${sessionScope.next}">[下一页]</a>
        </c:if>
        <a href="show?start=${sessionScope.last}">[末页]</a>
    </div>

    <div>
        <form action="buy">
            请输入要购买的书名:<input type="text" name="buyName"><br>
            请输入要购买的数量:<input type="text" name="buyNum"><br>
            <input type="submit" value="购买">
        </form>
    </div>

    <b><a href="page.jsp">注销</a></b>
    <b><c:out value="${sessionScope.successBuy}" default=""></c:out></b>
</body>
</html>
