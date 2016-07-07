<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
   <meta charset="UTF-8">
   <title>login</title>
   <script type="text/javascript"	src="./js/jquery-easyui-1.4.5/jquery.min.js"></script>
	<script type="text/javascript"	src="./js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/themes/icon.css">
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/demo/demo.css">
	
  </head>
  
  <body>
  
  <h2>Basic TextBox</h2>
    <p>The textbox allows a user to enter information.</p>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="Register" style="width:100%;max-width:400px;padding:30px 60px;">
      
        <div style="margin-bottom:20px">
           <label class="label-top">User Name:</label>
            <input class="easyui-textbox" style="width:100%;height:32px" value='${user.username}'>
        </div>
        <div style="margin-bottom:20px">
            <label class="label-top">Password:</label>
           <input class="easyui-textbox" style="width:100%;height:32px">
       </div>
        
       
    </div>
  
   
  </body>
</html>