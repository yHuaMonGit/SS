<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/1/7
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>有家宠物互联平台</title>

    <link rel="stylesheet" type="text/css" href="./static/tmp/css/style.css">

    <script type="text/javascript" src="./static/tmp/js/jquery.min.js"></script>
    <script type="text/javascript" src="./static/tmp/js/vector.js"></script>
    <script type="text/javascript" src="./static/tmp/js/login.js"></script>

</head>
<body>

<div id="container">
    <div id="output">
        <div class="containerT">
            <h1>用户登录</h1>
            <form class="form" id="entry_form">
                <input type="text" placeholder="用户名" id="entry_name" >
                <input type="password" placeholder="密码" id="entry_password">
                <button type="button" id="entry_btn" onclick="submitLogin()">登录</button>
                <div id="prompt" class="prompt"></div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登陆背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
</script>

</body>
</html>
