<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/7
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="javasctipt:location.href='/ssmshiro/user/doregister'">注册</button>
<button onclick="javasctipt:location.href='/ssmshiro/user/dodel'">删除</button>
<form action="/ssmshiro/login/do" method="post">
    用户名：<input name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value=登录>
</form>

</body>
</html>
