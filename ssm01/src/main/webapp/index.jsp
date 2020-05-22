<%--
  Created by IntelliJ IDEA.
  User: 79366
  Date: 2020/5/21
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
    <a href="account/findAll">测试findAll</a>

    <form action="account/saveAccount" method="post">
        姓名：<input type="text" name="name"/>
        金额：<input type="text" name="money"/>
        <input type="submit" value="保存"/>
    </form>

</body>
</html>
