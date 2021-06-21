<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page errorPage="error.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
注释1212
<!--html 注释-->
<%-- 编译成功，执行错误 ---%>
<%--<%--%>
    <%--int i =  5/ 0;--%>
<%--%>--%>

<%int a = 1;%>
<%=a%>
<%--静态引入--%>
<%@include file="includejsp.jsp"%>
<jsp:include page="activeJsp.jsp"></jsp:include>
<jsp:forward page="forward.jsp">
    <jsp:param name="forwardName" value="aaa"></jsp:param>
</jsp:forward>

</body>
</html>
