<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- list.css -->
<link rel="stylesheet" href ="/HugHug2/view/list.css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/HugHug2/assets/assets_main/css/main.css" />

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
 



<title>Visualize by TEMPLATED</title>
</head>


<script type="text/javascript">
var rowIdx = 1;
var rowCount = 3;

var loopCount = 6;

$(window).scroll(function() {
	var maxHeight = $(document).height();
	var currentScroll = $(window).scrollTop() + $(window).height();

	if (maxHeight <= currentScroll + 50) {
	
		var thumb = document.getElementById('dummy_thumb').cloneNode(true);
		test1(thumb);
	}
});

function test(thumb) {
	for(i = 0; i < loopCount; i++) {
		var targetRow = "thumb_container_row" + rowIdx;
		
		console.log(targetRow);
		
		$("#" + targetRow).append(thumb);



		rowIdx = (rowIdx % rowCount) + 1;
	}
}

function test1(thumb) {
	var targetRow = "thumb_container_row" + rowIdx;
	
	console.log(targetRow);
	
	$("#" + targetRow).append(thumb);


	rowIdx = (rowIdx % rowCount) + 1;
}
</script>

<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<span class="avatar"><a href="Main"><img src="/HugHug2/assets/assets_main/images/avatar.jpg" alt="" /></a></span>
						<div style="margin-bottom:25px; margin-top:-20px;">
						
						<a href="${pageContext.request.contextPath}/board/myPage">mypage</a>
						 &nbsp;  &nbsp;  &nbsp;
						<a href="${pageContext.request.contextPath}/board/Logout">logout</a>
						</div>
						<!-- <h1>This is <strong>your space</strong>. Have a good time!<br /> -->
						
		
						<div class="txt_ko">
							<h1>
								<strong>${userName}님 환영합니다.</strong>
							</h1>
						</div>
						
						<ul class="icons">
							<!-- 일기쓰기 페이지 적용하였습니다. jsp를 바로 호출하고 있습니다. 컨트롤러를 통해서 이동하려면 경로를 수정해야 합니다. -->
							<li><a href="diaryWrite" class="icon style2 fa-pencil-square-o"><span class="label">일기쓰기</span></a></li>
							<li><a href="searchList" class="icon style2 fa-list-ul"><span class="label">검색</span></a></li>
							
							<!-- 감정 그래프 페이지 적용하였습니다. jsp를 바로 호출하고 있습니다. 컨트롤러를 통해서 이동하려면 경로를 수정해야 합니다. -->
							<li><a href="chart" class="icon style2 fa-bar-chart"><span class="label">감정그래프</span></a></li>
						</ul>
					</header>

				<!-- Main -->
				 <table class="w3-table-all" width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
      
         <td>날짜</td>
         <td>제목</td>
         <td>첨부파일</td>
         <td >감정</td>
         <td >사용자메일</td>
         <c:forEach var="article" items="${articleList}">
         <tr>
         
         <td id = "regdate">${article.regdate}</td>
         <td id = "title">${article.title}</td>
		  <td id = "imagename">${article.imagename}</td>
		  <td id = "emotion">${article.emotion}</td>
		  <td id = "useremail">${article.useremail}</td>
		<tr>
      </tr></c:forEach>
   </table>
    <!--페이지 처리 -->
    <div style="text-align: center; margin-bottom: 10px;"> 
 		<c:if test="${count>0}">
		     
			<c:if test="${startPage} > ${bottomLine}">
			   <a href="searchList?pageNum=${startPage - bottomLine}">[이전]</a>
			   </c:if>
			   
			    <c:forEach var="i" begin="${startPage}" end="${endPage}">   
			   		<a href="searchList?pageNum=${i}">
				   <c:if test="${i !=  currentPage}">[${i}]</c:if>
				   <c:if test="${i ==  currentPage}">
				   <font color='red'>[${i}]</font></c:if></a>
			   </c:forEach>
			   
			   <c:if test="${endPage < pageCount}">
			      <a href="searchList?pageNum=${startPage+bottomLine}">[다음]</a>
			   </c:if>  
		</c:if> 
   </div>  

<!-- 회원 검색 -->
<div class="container-fluid">
   <div class="col-md-4 col-md-offset-4">
      <label> Search </label>
         <div class="form-group">
            <div class="input-group">
            <form method="post" action="searchList">
            <select name="opt">
            	<option value = "0">제목</option>
            	<option value = "1">내용</option>
            	<option value = "2">제목+내용</option>
            	<option value = "3">감정</option>
            	</select>
               <input type="text" class="form-control input-md" name="condition">
                  <div class="input-group-btn">
                     <button type="submit" name = "condition" class="btn btn-md btn-danger disabled"> 
                     <i class ="fa fa-search"></i></button>
                  </div>
                  </form>
            </div>
         </div>
   </div>
</div>
				


				<!-- Footer -->
					<footer id="footer">
						<p>&copy; Untitled. All rights reserved. Design: <a href="http://templated.co">TEMPLATED</a>. Demo Images: <a href="http://unsplash.com">Unsplash</a>.</p>
					</footer>

			</div>
		<!-- Scripts -->
		<script src="/HugHug2/assets/assets_main/js/jquery.min.js"></script>
		
			<script src="/HugHug2/assets/assets_main/js/jquery.poptrox.min.js"></script>
			<script src="/HugHug2/assets/assets_main/js/skel.min.js"></script>
			<script src="/HugHug2/assets/assets_main/js/main.js"></script>

	</body>
</html>
