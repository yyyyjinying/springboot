<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/5/26
  Time: 下午8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="demo4" method="post">
      <%--<label>用户名：</label><input type="text" name="username1">--%>
      <%--<label>用户密码：</label><input type="password" name="password1">--%>
        <%--<input type="checkbox" name="hover" value="学习"/>--%>
        <%--<input type="checkbox" name="hover" value="健身"/>--%>
        <%--<input type="checkbox" name="hover" value="看书"/>--%>
        <%--<input type="checkbox" name="hover" value="睡觉"/>--%>
      <%--</div>--%>

       <%--<label>用户名：</label><input type="text" name="user.username">--%>
       <%--<label>用户密码：</label><input type="password" name="user.password">--%>
     <label>用户名：</label><input type="text" name="list[0].username">
       <label>用户密码：</label><input type="password" name="list[0].password">
      <label>用户名：</label><input type="text" name="list[1].username">
       <label>用户密码：</label><input type="password" name="list[1].password">
      <input type="submit" value="提交"/>
    </form>

  <a href="demo5?id=123&name=刘杰">demo5</a>
  <a href="demo6/123/刘杰">demo/5</a>
  </body>
</html>
