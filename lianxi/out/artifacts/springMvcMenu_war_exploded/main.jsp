<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/6/3
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.7.2.js"></script>
    <script>

        $(function(){
            $("#aa").click(function(){
                var $td =$(this).parent().prev();
                //html()返回值字符串
                $td.html(parseInt($td.html())+1);
            })
        })
    </script>
</head>
<body>
    <table border="1">
        <tr>
            <td>资料名称</td>
            <td>下载次数</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${list}" var="file">
            <tr>
                <td>${file.name}</td>
                <td>${file.count}</td>
                <td><a id="aa" href="wdownload?id=${file.id}&name=${file.name}">下载</a> </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
