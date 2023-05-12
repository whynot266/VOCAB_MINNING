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


         <div class="container">

            <form action="<c:url value="j_spring_security_check"/>" method="post" id="login-form">
               <div class="keyboard" >
                  <div class="input-box">
                  <div class="input" >
                     <label for="name"></label>
                     <input type="text" name="username" id="email">
                     <span class="spin"></span>
                  </div>

                  <div class="input">
                     <label for="pass"></label>
                     <input type="password" name="password" id="password">
                     <span class="spin"></span>
                  </div>

                  </div>
                  <div>
                     <button class="keyboard__key" type="submit" data-key="c">
                     		<span class="keyboard__key-lines">
                     			<span class="keyboard__key-line">GO!</span>
                     		</span>
                     	</button>
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  </div>
            </form>


               </div>



         </div>
    </body>
</html>
