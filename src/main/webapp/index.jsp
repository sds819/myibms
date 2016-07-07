<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../js/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%=request.getContextPath()%>
<%=request.getRequestURI()%>
<html>
  <head>
  
   <meta charset="UTF-8">
   <title>Basic TextBox - jQuery EasyUI Demo</title>
  </head>
  
  <body>
  
  <script type="text/javascript">
	
			//alert('hi...');
			 
			 
			 $(function(){
				  //alert(11);
				  //$.messager.alert('提示信息','我是信息内容!');
				  //alert(11);
				  //$.messager.alert('提示信息','我是信息内容!','error');
				  /*
				  $.messager.confirm('标题内容' , '确认么?' ,function(r){
					  	if(r){
					  		alert('点击确认');
					  	} else {
					  		alert('点击取消');
					  	}
				  });
				  */
				  /*
				  $.messager.prompt('提示信息' , '请输入内容:' ,function(val){
					  alert(val);
				  });
				  */
				  /*
				  $.messager.progress({
					  title: '我是进度条' ,
					  msg:'文本内容' ,
					  text: '正在加载..' ,
					  interval:1000
				  });
				  */
				  $.messager.show({
					  title: '提示信息' , 
					  msg: '我是内容'
				  });
			 });
			
	</script>
  
  <h2>Basic TextBox</h2>
    <p>The textbox allows a user to enter information.</p>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="Register" style="width:100%;max-width:400px;padding:30px 60px;">
        <div style="margin-bottom:20px">
            <label class="label-top">Email:</label>
            <input class="easyui-textbox" data-options="prompt:'Enter a email address...',validType:'email'" style="width:100%;height:32px">
        </div>
        <div style="margin-bottom:20px">
           <label class="label-top">First Name:</label>
            <input class="easyui-textbox" style="width:100%;height:32px" value='welcome '>
        </div>
        <div style="margin-bottom:20px">
            <label class="label-top">Last Name:</label>
           <input class="easyui-textbox" style="width:100%;height:32px">
       </div>
        <div style="margin-bottom:20px">
           <label class="label-top">Company:</label>
            <input class="easyui-textbox" style="width:100%;height:32px">
        </div>
        
        <div>
           <a href="#" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Register</a>
        </div>
    </div>
  
   
  </body>
</html>