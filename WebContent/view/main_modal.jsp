<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="/HugHug2/assets/assets_main/css/main.css" />



<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script> 

<title>Visualize by TEMPLATED</title>
</head>

<script type="text/javascript">
var rowIdx = 1;
var rowCount = 3;

var loopCount = 6;

/* 스크롤 다운하면 이 곳에서 이벤트가 발생합니다. */
$(window).scroll(function() {
	var maxHeight = $(document).height();
	var currentScroll = $(window).scrollTop() + $(window).height();

	if (maxHeight <= currentScroll + 50) {
	
		var thumb = generateArticle("/HugHug2/assets/assets_main/images/fulls/01.jpg", "test");
		
		/* for(i = 0; i < 7; i++) {
			var newThumb = document.getElementById('dummy_thumb').cloneNode(true);
			test1(newThumb);
		} */
		
		/* var thumb = addArticle(imagename, content); */
		
		// 데이터베이스로부터 일기를 받아옵니다.
		// 받은 일기를 java script로 Array로 변환합니다.
		/* var arr = new Array();
		<c:forEach var="article" items="${articleList}" varStatus="status">
			arr[${status.index}] = "{state}";
		</c:forEach> */
		
		// 변환한 내용을 바탕으로 일기 그리드를 생성합니다.
		
	}
});

function getRecentDiary(articles) {
 
/* 	<c:forEach var="article" items="${articles}" varStatus="status">
	</c:forEach> */
}

function onClick(element) {
	  document.getElementById("diaryImage").src = element.src;
	  document.getElementById("diaryModal").style.display = "block";
}


// add article to main page
function generateArticle(imagename, content) {
	var newThumb = document.getElementById('dummy_thumb').cloneNode(true);
	newThumb.href = imagename;
	
	var childElement = newThumb.children;
	
	// article.imagename
	childElement[0].src = imagename;
	// aritcle.content  
	childElement[1].innerHTML = content;
	
	return newThumb;
}

function attachToPage(article) {
	var targetRow = "thumb_container_row" + rowIdx;
	console.log(targetRow);
	
	$("#" + targetRow).append(thumb);
	rowIdx = (rowIdx % rowCount) + 1;
}

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
<!-- modal div -->
<div id="diaryModal" class="w3-modal w3-animate-opacity">
	<div class="w3-modal-content w3-card-4" style="width:50%; min-width:300px; max-width:500px;">
		<header class="w3-container w3-teal"> 
			<span onclick="document.getElementById('diaryModal').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			<h2>Modal Title</h2>
		</header>
	
		<div class="w3-center">
			<img id="diaryImage" style="width:100%;">
		</div>
	
		<!-- diary content -->	
		<footer class="w3-container w3-teal">
			<p>Modal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal Footer</p>
			<p>Modal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal Footer</p>
			<p>Modal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal Footer</p>
			<p>Modal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal FooterModal Footer</p>
		</footer>
	</div>
	<div class="w3-bar w3-center w3-padding">
		<button class="w3-button w3-teal">Confirm</button>
		<button class="w3-button w3-teal">Rewrite</button>
		<button class="w3-button w3-teal">Delete</button>
	</div>
</div>
	  
	<!-- Wrapper -->
	<div id="wrapper">

	<!-- Header -->
		<header id="header">
			<span class="avatar"><img src="/HugHug2/assets/assets_main/images/avatar.jpg" alt="" /></span>
			<div style="margin-bottom:25px; margin-top:-20px;">
			
			<a href="${pageContext.request.contextPath}/board/mypage">mypage</a>
			 &nbsp;  &nbsp;  &nbsp;
			<a href="${pageContext.request.contextPath}/board/Logout">logout</a>
			</div>
			
			<div class="txt_ko">
				<h1>
					<strong>${userName}님 환영합니다.</strong>
				</h1>
			</div>
			
			<ul class="icons">
				<!-- 일기쓰기 페이지 적용하였습니다. jsp를 바로 호출하고 있습니다. 컨트롤러를 통해서 이동하려면 경로를 수정해야 합니다. -->
				<li><a href="diaryWrite.jsp" class="icon style2 fa-pencil-square-o"><span class="label">일기쓰기</span></a></li>
				<li><a href="#" class="icon style2 fa-search"><span class="label">검색</span></a></li>
				
				<!-- 감정 그래프 페이지 적용하였습니다. jsp를 바로 호출하고 있습니다. 컨트롤러를 통해서 이동하려면 경로를 수정해야 합니다. -->
				<li><a href="chart.jsp" class="icon style2 fa-bar-chart"><span class="label">감정그래프</span></a></li>
			</ul>
		</header>

<!-- ================ Ref code ================ -->
				<!-- Main -->
				 <table class="w3-table-all" width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
         <td >번호</td>
         <td >날짜</td>
         <td >내용</td>
         <td>첨부파일</td>
         <td >감정</td>
         <td >사용자메일</td>
         <c:forEach var="article" items="${articleList}">
         <tr>
         <td>${article.num}</td>
         <td>${article.regdate}</td>
         <td>${article.content}</td>
		  <td>${article.imagename}</td>
		  <td>${article.emotion}</td>
		  <td>${article.useremail}</td>
		<tr>
      </tr></c:forEach>
   </table>
<!-- ================ Ref code ================ -->
   
	<!-- Dummy Thumbnails -->
	<section style="display:none">
		<section class="thumbnails">
			<div>
				<a id="dummy_thumb">
					<img src="/HugHug2/assets/assets_main/images/fulls/06.jpg" style="width:100%;cursor:pointer" onclick="onClick(this)" class="w3-hover-opacity">
					<h3>2018 . 02 . 12 MON</h3>
				</a>
			</div>
		</section>
	</section>

	<!-- diary grid section -->
	<section id="main">
		<!-- Thumbnails -->
		<section id="thumbnailImages" class="thumbnails">
			<div id="thumb_container_row1">
				<!-- 모달 테스트 더미입니다. -->
				<a id="dummy_thumb">
					<img src="/HugHug2/assets/assets_main/images/fulls/06.jpg" style="width:100%;cursor:pointer" onclick="onClick(this)" class="w3-hover-opacity">
					<h3>2018 . 02 . 12 MON</h3>
				</a>
			</div>
			<div id="thumb_container_row2"></div>
			<div id="thumb_container_row3"></div>
		</section>
	</section>

	<!-- 데이터베이스로부터 최근 일기 7개를 받아서 화면에 뿌려줍니다. -->
	<c:forEach var="article" items="${articleList}">
		<script type="text/javascript">
			var article = generateArticle(${article.imagename}, ${article.regdate});
			attachToPage(article);
		</script>
	</c:forEach>

	<!-- https://stackoverflow.com/questions/13190306/set-array-values-in-javascript-from-jstl-code?rq=1 -->
	<script>
		var ids = [];
		i = 0;
	</script>
	
	<c:forEach begin="0" end="10" varStatus="loop">
		<script>
			ids.push(i);
			++i
			console.log(i);
			/* var a = addArticle("/HugHug2/assets/assets_main/images/fulls/07.jpg", "test")
			test1(a); */
		</script>
	</c:forEach>

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
