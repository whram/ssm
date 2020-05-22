<%--
  Created by IntelliJ IDEA.
  User: 79366
  Date: 2020/5/19
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery.min.js"></script>

    <script>
        //页面加载，绑定单击事件
        $(function(){
            $("#btn").click(function(){
                //alert("hello btn");
                //发送ajax请求
                $.ajax({
                    //编写json格式，属性和值
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"啊啊","password":"123","age":20}',
                    dataType:"jason",
                    type:"post",
                    success:function(data){
                        //data服务器端响应的jason的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });
            });
        });
    </script>

</head>
<body>

    <a href="user/testString">testString</a><br/>

    <a href="user/testVoid">testVoid</a><br/>

    <a href="user/testModelAndView">ModelAndView</a><br/>

    <a href="user/testForwardOrRedirect">OrRedirect</a><br/>

    <button id="btn">发送ajax的请求</button>

</body>
</html>
