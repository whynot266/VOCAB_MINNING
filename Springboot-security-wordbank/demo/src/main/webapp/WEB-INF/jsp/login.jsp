<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
         <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
         <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


    </head>
    <body>
       <div class="header" >
           <div class="sysbar">
               <a href="">COOKING SPY</a>
           </div>
       </div>

         <div class="materialContainer">

            <form action="<c:url value="j_spring_security_check"/>" method="post" id="login-form">
               <div class="box">

                  <div class="title">ĐĂNG NHẬP</div>

                  <div class="input">
                     <label for="name"></label>
                     <input type="text" name="username" id="email">
                     <span class="spin"></span>
                  </div>

                  <div class="input">
                     <label for="pass"></label>
                     <input type="password" name="password" id="password">
                     <span class="spin"></span>
                  </div>

                  <div >
                     <button class="login-btn" type="submit"><span>GO</span> <i class="fa fa-check"></i></button>
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  </div>
            </form>
                  <a href="/project-final/registrationForm" class="pass-forgot"><span>Chưa có tài khoản?</span> Đăng ký</a>

               </div>

               <div class="overbox">

               </div>

         </div>
    </body>
</html>
