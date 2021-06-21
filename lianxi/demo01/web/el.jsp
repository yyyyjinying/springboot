<%--
  Created by IntelliJ IDEA.
  User: yyyyjinying
  Date: 2021/5/12
  Time: 下午7:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="com.el.pojo.*" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<b><%=request.getParameter("uname")%></b><br />
<%--<b><%=request.getAttribute("str") %></b><br />--%>
<%--<b><%=((User)request.getAttribute("user")).getAddr().getTown() %></b><br />--%>
<%--<b><%=((ArrayList)request.getAttribute("list")).get(0)%></b>--%>
<%--<b><%=((User)((ArrayList)request.getAttribute("list2")).get(0)).getAddr().getCity()%></b>--%>
<%--<b><%=((HashMap)request.getAttribute("map")).get("c") %></b><br />--%>
<%--<b><%=((User)(((HashMap)request.getAttribute("map2")).get("a1"))).getAddr().getCity() %></b><br />--%>
<!-- 使用EL表达式获取作用域对象数据 -->
<h3>EL表达式学习：使用EL表达式获取作用域对象的数据</h3>
<%
    String str2="哈哈哈";
%>
<b>${param.uname}</b><br />

<%--http://localhost:8888/el01?uname=zhao&pwd=123&fav=1&fav=2--%>
<b>${paramValues.fav[0]}</b><br />

<b>${str}</b><br />
<b>${user}</b><br />
<b>${list[2]}</b><br />
<b>${list2[0].addr.pre}</b><br />
<b>${map.c}</b><br />
<b>${map2.a1.addr.city}</b><br />
<b>-${str2}</b> <br />
<b><%=str2%></b>
<hr />

<h3>EL的作用域查找顺序</h3>
<%
    pageContext.setAttribute("hello","hello pageContext");
    request.setAttribute("hello","hello request");
    session.setAttribute("hello","hello session");
    application.setAttribute("hello","hello application");
%>
${hello}

${pageScope.hello}---${requestScope.hello}--${sessionScope.hello}--${applicationScope.hello}
<h3>EL表达式的逻辑运算</h3>
${1+2}--${1*2}--${4/2}--${4-2}--${4%3}--${4==4}--${4>2}--${sex==1?'男':'女'}--${1+'2'}
<h3>EL表达式的空值判断</h3>
${empty s}--${empty s1}--${empty s2}---${empty s3}
<h3>EL获取请求头数据和Cookie数据</h3>
${header}--${headerValues["accept-language"][0]} <br /><br /><br />
${cookie}--${cookie.JSESSIONID}---${cookie.JSESSIONID.name}--${cookie.JSESSIONID.value}


</body>
</html>
