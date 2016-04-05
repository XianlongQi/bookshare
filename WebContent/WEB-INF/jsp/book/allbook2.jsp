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
<link href="<%=request.getContextPath()%>/static/style/mybook.css" rel="stylesheet" type="text/css" />
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
	                 <li><a href="<%=request.getContextPath()%>/allbook" style="background-color: #2B6C85;">全部图书</a></li>
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
          
           <div class="user_information">
           
               <div class="user_header">
                   <img src="<%=request.getContextPath()%>/static/images/MyBooks_IMG_DefaultAvatar_80x80.png"/>
                   <div class="user_name">齐显龙</div>
               </div>
               
               <div class="user_basis">
                   <div class="tip">
                    <label>基本资料</label><img src="<%=request.getContextPath()%>/static/images/BTN_Edit_20x20.png"/>
                   </div>
                   <ul>
                       <li><label>部门&nbsp;:&nbsp;</label><span>电子政务</span></li>
                       <li><label>电话&nbsp;:&nbsp;</label><span>18201659916</span></li>
                       <li><label>邮箱&nbsp;:&nbsp;</label><span>qxlstruggle@163.com</span></li>
                       <li><label>地址&nbsp;:&nbsp;</label><span>海淀区学清路</span></li>
                   </ul>
               </div>
	           <div class="user_message">
	              <div class="my_message">
		              <label>我的消息</label>
		              <label class="message_tip"></label>
		              <label class="tip_count">5</label>
	              </div>
	              <div class="my_request">
	                  <label>借书请求</label>
                      <label class="message_tip"></label>
                      <label class="tip_count">6</label>
	              </div>
	           </div>
           </div>
        </div>

        <div id="content">
            <div id="myBookTab">
               <ul>
                  
                   <li><a href="#" >全部</a><span>15</span></li>
                   <li><a href="#" >已借出</a><span>4</span></li>
                   <li><a href="#" >未借出</a><span>12</span></li>
                   <li><a href="#" style="border-right: 1px solid #8EBACA;">借入</a><span>3</span></li>
               </ul>
                <div> <a href="<%=request.getContextPath()%>/editbook">新增图书</a></div>
             </div>
            <!-- Table 内容开始-->
            <div class="table">
                 <!-- Table Header开始-->
                <div class="header">
                  <ul>
                    <li class="index">编号</li>
                    <li class="book_info" >书籍</li>
                    <li class="book_status">状态</li>
                    <li class="book_operator">操作</li>
                    <li class="book_history">借阅历史</li>
                  </ul>
                </div>
                <!-- Table Header结束-->
                
                <div class="content">
                
                  <!-- Table 行内容开始-->
                 <%
                 ArrayList<Book> books = null;
                int i = 0;
                books = (ArrayList<Book>)request.getAttribute("books");
                if(!books.isEmpty()){
                for(Book book : books){
                    i++;
                %>
                  <div>
                    <ul>
                      <li class="index"><%=i %></li>
                      <li class="book_info">
                         <img class="book_img" src="<%=request.getContextPath()%><%=book.getPicture()%>">
                         <label class="book_title" title=""><%=book.getName()%></label>
                         <label class="book_author" title=""><%=book.getAuthor()%></label>
                         
                      </li>
                    
                    <li class="book_status">
                          <img src="<%=request.getContextPath()%>/static/images/ICN_Holder_Blue_30x30.png">
                          <label class="holder_name"><%=book.getOwnerName() %></label>
                          <%if (book.getCurrentId() == book.getOwnerId()) { %>
                          <label class="status_dec">[未借出] </label>
                          <%} else{%>
                          <label class="status_dec">[已借出] </label>
                         <%} %>
                      </li>
                      <li class="book_operator">
                      
                        <div class="cell">
                         
                           <a class="update_button" href="#">更新</a>
                           <div class="update_button">删除</div>
                          
                          
                        </div>

                      </li>
                      <li class="book_history">
                         <div class="short_desc">
                          2011年11月4 &nbsp;:&nbsp;借给zack.liu <br />
                          2011年12月4 &nbsp;:&nbsp;借给billy.he <br />
                          2011年12月4 &nbsp;:&nbsp;录入系统 <br />
                         </div>
                         <img class="read_more" src="<%=request.getContextPath()%>/static/images/ICN_ReadMore_30x10.png"/>
                      </li>
                    </ul>
                  </div>
                  <!-- Table 行内容结束-->
                  <%}
                }%>
                 </div>
                 
                
            </div>
             <!-- Table 内容结束-->
             
           <!-- 分页开始  -->
           
          
           <div class="pagination">
            <span class="first_page">
           
              <img src="<%=request.getContextPath()%>/static/images/MyBooks_BTN_FirstPage_Disable_20x20.png"/>
            
            </span>
            <span class="pre_page">
               
                 <a href="#">
                 <img src="<%=request.getContextPath()%>/static/images/MyBooks_BTN_PrePage_Normal_20x20.png"/>
                 </a>
               
            </span>
            <span class="current_page"> 第3页</span>
            <span class="next_page">
             
               <a href="#">
               <img src="<%=request.getContextPath()%>/static/images/MyBooks_BTN_NextPage_Normal_20x20.png"/>
               </a>
             
            </span>
            <span class="last_page">
               
               <a href="#">
               <img src="<%=request.getContextPath()%>/static/images/MyBooks_BTN_LastPage_Normal_20x20.png"/>
               </a>
              
            </span>
            <span class="total_page"> 共5页</span>
            <span class="go_desc">到第</span>
              <input type="text" class="go_input" id="currentPage" name="currentPage" value=""/>
            <span class="go_page"> 页</span>
            <div class="go_button" onclick="">GO</div>
          </div>
          
           <!-- 分页结束  -->
        
     </div>
     </div>
	</div>
	
	<script>
		$(document).ready(function(){
		  $("#btn_mybook").click(function(){
			    alert();
		  });
		});
	</script>

</body>
</html>