<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/5/8
  Time: 上午8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My JSP 'Method.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>

    <%--<form action="method" method="post">--%>
        <%--用户名: <input type="text" name="uname" value="" /><br />--%>
        <%--密码: <input type="text" name="pwd" value="" /><br />--%>
        <%--爱好：<br />--%>
        <%--<input type="checkbox" name="fav" value="1"/>唱歌<br />--%>
        <%--<input type="checkbox" name="fav" value="2"/>跳舞<br />--%>
        <%--<input type="checkbox" name="fav" value="3"/>游泳<br />--%>

        <%--<input type="submit" value="登录" />--%>
    <%--</form>--%>

    <form action="my" method="get">
        用户名: <input type="text" name="uname" value="" /><br />
        密码: <input type="text" name="pwd" value="" /><br />
        爱好：<br />
        <input type="checkbox" name="fav" value="1"/>唱歌<br />
        <input type="checkbox" name="fav" value="2"/>跳舞<br />
        <input type="checkbox" name="fav" value="3"/>游泳<br />

        <input type="submit" value="登录" />

    </form>

</body>
</html>
