<%--
  Created by IntelliJ IDEA.
  User: 79366
  Date: 2020/5/18
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--常用的注解--%>
    <a href="anno/testRequestParam?uname=啊啊">RequestParam</a>

    <form action="anno/testRequestBody" method="post">
        用户名：<input type="text" name="uname"/><br/>
        用户年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/><br/>
    </form>

    <a href="anno/testPathVariable/10">PathVariable</a><br/>

    <a href="anno/testRequestHeader">RequestHeader</a><br/>

    <a href="anno/testCookieValue">CookieValue</a><br/>

    <form action="anno/testModelAttribute" method="post">
        用户名：<input type="text" name="uname"/><br/>
        用户年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/><br/>
    </form>

    <a href="anno/testSessionAttributes">SessionAttributes</a><br/>

    <a href="anno/testGetSessionAttributes">GetSessionAttributes</a><br/>

    <a href="anno/testDelSessionAttributes">DelSessionAttributes</a><br/>

</body>
</html>
