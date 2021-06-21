<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
        <c:forEach items="${PageInfo.list}" var="pi">
            <tr>
                <td>${pi.id}</td>
                <td>${pi.name}</td>
                <td>${pi.age}</td>
            </tr>
        </c:forEach>
    </table>
    <div>${PageInfo.total}</div>
    <a href="page?pageSize=${PageInfo.pageSize}&pageNumber=${PageInfo.pageNumber-1}" <c:if test="${PageInfo.pageNumber<=1}"> onclick="javascript:return false;" </c:if>>上一页</a>
    <a href="page?pageSize=${PageInfo.pageSize}&pageNumber=${PageInfo.pageNumber+1}" <c:if test="${PageInfo.pageNumber>=PageInfo.total}"> onclick="javascript:return false;" </c:if>>下一页</a>

</body>
</html>
