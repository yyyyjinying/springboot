<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/5/20
  Time: 上午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
      $(function(){
          $("a").click(function(){
              $("img").attr("src","validcode?date="+new Date())
              return false;
          })

          $("#btn").click(function(){
              $.post("ajax",{"name":"张三"},function(data){
                  var result="";
                  for(var i =0 ;i<data.length;i++){
                      result+="<tr>";
                      result+="<td>"+data[i].id+"</td>"
                      result+="<td>"+data[i].username+"</td>"
                      result+="<td>"+data[i].password+"</td>"
                      result+="</tr>";
                  }
                  //相当innerHTML先清空后添加
                  $("#mytbody").html(result);
              })
          })
      })

    </script>
  </head>

  <body>
  <%--<img src="images/a.png"/>--%>
  ${error}
    <form action="login" method="post">
      用户名：<input type="text" name="username"/><br/>
      密码：<input type="password" name="password"/><br/>
      验证码：<input type="text" size="1" name="code"/><img src="validcode" width="80" height="40"/><a href="">看不清</a><br/>
      <input type="submit" value="登录"/><input type="reset" value="重置"/>
    </form>

  <div>
    <button id="btn">跳转</button>
    <table border="1">
      <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>密码</td>
      </tr>
      <tbody id="mytbody"></tbody>
    </table>
  </div>
  </body>
</html>
