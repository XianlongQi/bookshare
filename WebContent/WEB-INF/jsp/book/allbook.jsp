<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.qixl.bookshare.model.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的图书</title>
<link href="<%=request.getContextPath()%>/static/style/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/style/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/style/allbook.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.0.min.js"></script>
</head>
<body>
	<div class="warpper" id="mybookPage">
	    <div id="headerWarpper">
            <div id="header">
                <div class="logo" onclick="toIndex('<%=request.getContextPath()%>');">图书分享</div>
	            <div class="menu">
	               <ul>
	                 <li><a href="#" id="btn_mybook">我的图书</a></li>
	                 <li><a href="<%=request.getContextPath()%>/allbook.action" style="background-color: #2B6C85;">全部图书</a></li>
	               </ul>
	            </div>
				<div class="tool" >
	                <input name="searchKeyword" type="text">
	                <img class="search_button" src="<%=request.getContextPath()%>/static/images/BTN_Search_30x30.png"></img>
	                <img class="role_switch_button" src="<%=request.getContextPath()%>/static/images/BTN_SwitchRole_User_35x35.png"></img>
                </div>
			</div>
		</div>
		
		<div id="breadcrumb">
            <ul>
                   <li>当前位置 &nbsp;&nbsp;:</li>
                   <li><a href="#">全部图书</a></li>
             </ul>
        </div>
      
		
        <div class="main" id="mybookMain">
        <div id="left">
           <div id="leftBinner" class="leftBinner">
	           	<div class="leftTitle">
	           		全部图书
	           	</div>
	           	<div>
	           		<ul>
	           			<li><a href="#">计算机类</a></li>
	           			<li><a href="#">设计</a></li>
	           			<li><a href="#">管理</a></li>
	           			<li><a href="#">人文</a></li>
	           			<li><a href="#">其他类</a></li>
	           		</ul>
	           	</div>
           	</div>
           </div>
        

        <div id="content">
            <!--展示书信息开始 -->
            <div class="bookShow">
            	<div class="bookImage">
            	   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
            	</div>
            	<div class="bookName" >Java编程思想</div>
            	<div class="bookAuthor">谭浩强   编著</div>
            	<div class="bookBottom">
            	   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
            	   <span class="bookBottom_text">Mr.Qi</span>
            	</div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book_Java_BCSX.jpg" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
             <!--展示书信息开始 -->
            <div class="bookShow">
                <div class="bookImage">
                   <img src="<%=request.getContextPath()%>/static/book/Book1_50x60.png" />
                </div>
                <div class="bookName" >Java编程思想</div>
                <div class="bookAuthor">谭浩强   编著</div>
                <div class="bookBottom">
                   <img style="float:left;" src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                   <span class="bookBottom_text">Mr.Qi</span>
                </div>
            </div>
            <!--展示书信息结束 -->
            
        </div>
     </div>
	</div>
	
	<script>
		$(document).ready(function(){
		  $("#btn_mybook").click(function(){
			  $.ajax({
				  type: "GET",
				  url: "<%=request.getContextPath()%>"+"/mybook.action",
				  dataType: "script",
				  success:function (){alert('sss');}
				});
			    
		  });
		});
	</script>

</body>
</html>