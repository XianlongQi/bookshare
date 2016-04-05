<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Map" %>
<%@ page import = "java.util.HashMap" %>
<%@ page import = "com.qixl.bookshare.Constants" %>

<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
  </head>
  <style>
  .red{color:red;}
  </style>
<body onkeydown="keyLogin();">
    <%String tipMessage = (String)request.getAttribute(Constants.TIP_MESSAGE);%>
    <%if(tipMessage != null){ %>
         <%=tipMessage%>
    <%} %>
    <% 
        Map<String,String> errorMap = (Map<String,String>)request.getAttribute(Constants.ERROR_MESSAGE);
        if(errorMap==null){
            errorMap = new HashMap<String,String>();
        }
    %>
    
    <form action="login" method="POST" id="loginForm">
    <span>UserName:</span><input type="text" name="userName" id="userName"/>
    <span id="tip_userName" class="red">
        <%
            String userNameErrorMessage = errorMap.get("userName") == null ? "" :errorMap.get("userName") ; 
            out.print(userNameErrorMessage);
        %></span><br/>
    <span>Password:</span><input type="password" name="password" id="password"/>
    <span id="tip_password" class="red">
        <%
            String passwordErrorMessage = errorMap.get("password") == null ? "" :errorMap.get("password") ;  
            out.print(passwordErrorMessage);
        %>
    </span><br/>
    <input type="button" value="Login" id="btn_login" onclick="login()"/>
    </form>
    <script>
        function login(){
            var loginFormObj = document.getElementById("loginForm");
            var userName = document.getElementById("userName").value;
            var password = document.getElementById("password").value;
            var isSubmit = true;
            if (!userName) {
                document.getElementById("tip_userName").innerHTML = "userName不能为空";
                isSubmit = false;
            }
                if (!password) {
                    document.getElementById("tip_password").innerHTML = "password不能为空";
                    isSubmit = false;
                }
            if (!isSubmit) {
                return;
            }
            var userName = document.getElementById("userName").value;
            loginFormObj.submit();
        }
        
        function  keyLogin() {
	        if (event.keyCode==13) {                        
	        	//按Enter键的键值为13  
	        	     document.getElementById("btn_login").click(); 
	        }
        }
    </script>
</body>
</html>