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

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 
<title>Visualize by TEMPLATED</title>
</head>


<script type="text/javascript">
var rowIdx = 1;
var rowCount = 3;

var loopCount = 6;

function onClickDiaryRow(element) {
	var childElement = element.children;
	
	document.getElementById("diaryImage").src = "${pageContext.request.contextPath}/images/" + childElement[2].innerHTML;
	document.getElementById("diaryTitle").innerHTML = childElement[1].innerHTML;
	document.getElementById("diaryContent").innerHTML = childElement[5].innerHTML;
	document.getElementById("diaryNum").innerHTML = childElement[6].innerHTML;
	document.getElementById("diaryModal").style.display = "block";
}

function deleteArticle() {
	document.getElementById('confirmModal').style.display='none';
	document.getElementById('diaryModal').style.display='none';
	location.href="DeletediaryPro?num=" + document.getElementById('diaryNum').innerHTML;
	console.log("DeletediaryPro?num=" + document.getElementById('diaryNum').innerHTML);
}

function updateArticle() {
	document.getElementById('confirmModal').style.display='none';
	document.getElementById('diaryModal').style.display='none';
	location.href="diaryUpdateForm?num=" + document.getElementById('diaryNum').innerHTML;
	console.log("diaryUpdateForm?num=" + document.getElementById('diaryNum').innerHTML);
}

</script>

<body style="font-family:NanumBarunpenR">

<div id="diaryModal" class="w3-modal w3-animate-opacity">
		<div class="w3-modal-content w3-card-4" style="width:50%; min-width:300px; max-width:500px;">
			<header class="w3-container w3-grey">
				<span onclick="document.getElementById('diaryModal').style.display='none'" class="w3-button w3-display-topright">&times;</span>
				<h2 id="diaryTitle">Modal Title</h2>
			</header>
		
			<div class="w3-center w3-round">
				<img id="diaryImage" style="width:100%;">
			</div>
		
			<!-- diary content -->	
			<footer class="w3-container w3-light-grey">
				<p id="diaryContent">Modal Content</p>
				<p id="diaryNum" style="display:none">Modal Num</p>
			</footer>
		</div>
		<div class="w3-bar w3-center w3-padding txt_ko">
			<button class="w3-button w3-sand" onclick="document.getElementById('diaryModal').style.display='none'">Confirm</button>
			<button class="w3-button w3-sand" onclick="updateArticle()">Rewrite</button>
			<button class="w3-button w3-sand" onclick="document.getElementById('confirmModal').style.display='block'">Delete</button>
		</div>
	</div>

	<!-- modal div -->
	<div id="confirmModal" class="w3-modal w3-animate-opacity">
		<div class="w3-panel w3-modal-content w3-card-4 w3-light-grey" style="width:50%; min-width:300px; max-width:500px;">
			<div class="w3-panel w3-center">
				정말 지울고얌?
			</div>
			<div class="w3-bar w3-center w3-padding">
				<button class="w3-button w3-teal" onclick="document.getElementById('confirmModal').style.display='none'">No</button>
				<button class="w3-button w3-red" onclick="deleteArticle()">Yes</button>
			</div>
		</div>
		
	</div> 

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
		
						<div>
							<h1>
								<strong class="txt_ko">${userName}님 환영합니다.</strong>
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
<table width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
      
         <td>날짜</td>
         <td>제목</td>
         <td>첨부파일</td>
         <td >감정</td>
         <td >사용자메일</td>
         <c:forEach var="article" items="${articleList}">
        
         <tr onclick="javascript:onClickDiaryRow(this)">

         <!-- title link to modal -->
         <td id = "regdate">${article.regdate}</td>
         <td id = "title">${article.title}</td>
		  <td id = "imagename">${article.imagename}</td>
		  <td id = "emotion">${article.emotion}</td>
		  <td id = "useremail">${article.useremail}</td>
		  <td style="display:none">${article.content}</td>
		  <td style="display:none">${article.num}</td>
		  
     		 </tr>
         
      	</c:forEach>
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

<div class="w3-row">
	<div class="w3-col w3-container" style="width:30%"></div>
  
	<form method="post" action="searchList">
		<div class="w3-col" style="width:10%">
			<select name="opt">
            	<option style="background: rgba(170, 170, 170, 1.0)" value="0">제목</option>
            	<option style="background: rgba(170, 170, 170, 1.0)" value="1">내용</option>
            	<option style="background: rgba(170, 170, 170, 1.0)" value="2">제목+내용</option>
            	<option style="background: rgba(170, 170, 170, 1.0)" value="3">감정</option>
            </select>
		</div>
		<div class="w3-col" style="width:20%">
			<input type="text" name="condition">
			
		</div>
		<div class="w3-col" style="width:20%">
			<button type="submit" name="condition" class="btn btn-md btn-danger disabled"> 
				<i class ="fa fa-search"></i>
			</button>
		</div>
	</form>
	
	<div class="w3-col w3-container" style="width:30%"></div>
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
