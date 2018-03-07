<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/HugHug2/assets/assets_main/css/main.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script> 

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
						
						<a href="${pageContext.request.contextPath}/board/mypage">mypage</a>
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
							<li><a href="searchList" class="icon style2 fa-search"><span class="label">검색</span></a></li>
							
							<!-- 감정 그래프 페이지 적용하였습니다. jsp를 바로 호출하고 있습니다. 컨트롤러를 통해서 이동하려면 경로를 수정해야 합니다. -->
							<li><a href="chart" class="icon style2 fa-bar-chart"><span class="label">감정그래프</span></a></li>
						</ul>
					</header>

				<!-- Main -->
				 <table class="w3-table-all" width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
        <!--  <td>날짜</td>
         <td>제목</td>
         <td>첨부파일</td>
         <td >감정</td>
         <td >사용자메일</td>
         <c:forEach var="article" items="${articleList}">
         <tr>
         <td>${article.regdate}</td>
         <td>${article.title}</td>
         		  <td>${article.imagename}</td>
         		  <td>${article.emotion}</td>
         		  <td>${article.useremail}</td>
         		<tr>
               </tr></c:forEach> -->
   </table>
				<section style="display:none">

						<!-- Thumbnails -->
							<section class="thumbnails">
								<div>
									<a id="dummy_thumb" href="/HugHug2/assets/assets_main/images/fulls/01.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/01.jpg" alt="" />
										<h3>2018 . 02 . 12 MON</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/02.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/02.jpg" alt="" />
										<h3>2018 . 02 . 13 TUE</h3>
									</a>
								</div>
								<div>
									<a href="/HugHug2/assets/assets_main/images/fulls/03.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/03.jpg" alt="" />
										<h3>2018 . 02 . 14 WED</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/05.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/05.jpg" alt="" />
										<h3>2018 . 02 . 16 FRI</h3>
									</a>
								</div>
								<div>
									<a href="/HugHug2/assets/assets_main/images/fulls/06.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/06.jpg" alt="" />
										<h3>2018 . 02 . 17 SAT</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/07.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/07.jpg" alt="" />
										<h3>2018 . 02 . 18 SUN</h3>
									</a>
								</div>
							</section>
				</section>
						
					<section id="main">

						<!-- Thumbnails -->
							<section id="thumbnailImages" class="thumbnails">
								<div id="thumb_container_row1">
									<a href="/HugHug2/assets/assets_main/images/fulls/01.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/01.jpg" alt="" />
										<h3>2018 . 02 . 12 MON</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/02.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/02.jpg" alt="" />
										<h3>2018 . 02 . 13 TUE</h3>
									</a>
								</div>
								<div id="thumb_container_row2">
									<a href="/HugHug2/assets/assets_main/images/fulls/03.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/03.jpg" alt="" />
										<h3>2018 . 02 . 14 WED</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/04.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/04.jpg" alt="" />
										<h3>2018 . 02 . 15 THU</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/05.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/05.jpg" alt="" />
										<h3>2018 . 02 . 16 FRI</h3>
									</a>
								</div>
								 <div id="thumb_container_row3">
									<a href="/HugHug2/assets/assets_main/images/fulls/06.jpg">
								
										<img src="/HugHug2/assets/assets_main/images/thumbs/06.jpg" alt="" />
										<h3>2018 . 02 . 17 SAT</h3>
									</a>
									<a href="/HugHug2/assets/assets_main/images/fulls/07.jpg">
										<img src="/HugHug2/assets/assets_main/images/thumbs/07.jpg" alt="" />
										<h3>2018 . 02 . 18 SUN</h3>
									</a>
								</div>
							</section>
					</section>


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
