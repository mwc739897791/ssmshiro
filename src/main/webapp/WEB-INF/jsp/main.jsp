<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/7
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}
<s:hasPermission name="bookmanager:book:query">
    可以查询
</s:hasPermission>
<s:hasPermission name="bookmanager:book:add">
    可以增加
</s:hasPermission>
<s:hasPermission name="bookmanager:book:del">
    可以删除
</s:hasPermission>
</body>
</html>
