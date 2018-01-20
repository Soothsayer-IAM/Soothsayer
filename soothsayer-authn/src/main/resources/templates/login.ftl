<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Material Compact Login Animation</title>


<#--<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>-->
<#--<link rel='stylesheet prefetch'-->
<#--href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900&subset=latin,latin-ext'>-->
    <link rel="stylesheet" href="../assets/css/login.css">

</head>

<body>

<div class="materialContainer">


    <div class="box">
        <div class="title">登陆</div>
        <form action="/authn/form/login" method="POST">
            <div class="input">
                <label for="name">用户名</label>
                <input type="text" name="username" id="name">
                <span class="spin"></span>
            </div>

            <div class="input">
                <label for="pass">密码</label>
                <input type="password" name="password" id="pass">
                <span class="spin"></span>
            </div>
            <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="button login">
                <button type="submit"><span>GO</span> <i class="fa fa-check"></i></button>
            </div>
            <a href="" class="pass-forgot">Forgot your password?</a>
        </form>
    </div>

    <div class="overbox">
        <div class="material-button alt-2"><span class="shape"></span></div>

        <div class="title">REGISTER</div>

        <div class="input">
            <label for="regname">Username</label>
            <input type="text" name="regname" id="regname">
            <span class="spin"></span>
        </div>

        <div class="input">
            <label for="regpass">Password</label>
            <input type="password" name="regpass" id="regpass">
            <span class="spin"></span>
        </div>

        <div class="input">
            <label for="reregpass">Repeat Password</label>
            <input type="password" name="reregpass" id="reregpass">
            <span class="spin"></span>
        </div>

        <div class="button">
            <button><span>NEXT</span></button>
        </div>


    </div>

</div>
<script src='../assets/lib/jquery/jquery.min.js'></script>
<script src="../assets/js/login.js"></script>
</body>

</html>
