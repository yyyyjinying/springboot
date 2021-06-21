<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/5/28
  Time: 下午1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script>
      $(function(){
          $.post("show", function(data){
              debugger;
          })
      })
    </script>
  </head>
  <body>
    <a href="file/learnspring.zip">下载……</a>
    <a href="download?filename=a.txt">下载a……</a>
  <div>
    <form action="upload" enctype="multipart/form-data" method="post">
      姓名：<input type="text" name="name"/><br/>
      文件：<input type="file" name="file"/><br/>
      <input type="submit" value="提交"/>
    </form>
  </div>
  </body>
</html>
