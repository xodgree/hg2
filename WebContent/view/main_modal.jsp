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

<style>
</style> 

<title>Visualize by TEMPLATED</title>
</head>

<script type="text/javascript">
var rowIdx = 1;
var rowCount = 3;

var loopCount = 6;

/* ��ũ�� �ٿ��ϸ� �� ������ �̺�Ʈ�� �߻��մϴ�. */
$(window).scroll(function() {
	var maxHeight = $(document).height();
	var currentScroll = $(window).scrollTop() + $(window).height();

	if (maxHeight <= currentScroll + 50) {
		 $.ajax({
         });
	}
});

function getRecentDiary(articles) {
	
}

// �ϱ� �׸��带 Ŭ���ϸ�, ��޿� �ϱ� ������ �����մϴ�.
function onClickThumb(element) {
	var childElement = element.children;
	
	document.getElementById("diaryImage").src = childElement[0].src;
	document.getElementById("diaryTitle").innerHTML = childElement[2].innerHTML;
	document.getElementById("diaryContent").innerHTML = childElement[3].innerHTML;
	document.getElementById("diaryNum").innerHTML = childElement[4].innerHTML;
	document.getElementById("diaryModal").style.display = "block";
	
}

function attachToPage(imagename, regdate, title, content, num) {
	var newThumb = document.getElementById('dummy_thumb').cloneNode(true);
	newThumb.href = imagename;
	newThumb.style.display = "block";
	
	/* child
	0 - imagename
	1 - regdate
	2 - title
	3 - contet
	4 - num
	*/
	var childElement = newThumb.children;
	
	// article.imagename
	childElement[0].src = "${pageContext.servletContext.contextPath}" + "/images/" + imagename;
	childElement[1].innerHTML = regdate;
	childElement[2].innerHTML = title;
	childElement[3].innerHTML = content;
	childElement[4].innerHTML = num;
	
	console.log(childElement[0].src);
	
	var targetRow = "thumb_container_row" + rowIdx;
	console.log(targetRow);
	
	$("#" + targetRow).append(newThumb);
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

<body class="txt_ko">
	<!-- modal div -->
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
				���� ������?
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
			
			<div class="txt_ko">
				<h1>
					<strong>${userName}�� ȯ���մϴ�.</strong>
				</h1>
			</div>
			
			<ul class="icons">
				<!-- �ϱ⾲�� ������ �����Ͽ����ϴ�. jsp�� �ٷ� ȣ���ϰ� �ֽ��ϴ�. ��Ʈ�ѷ��� ���ؼ� �̵��Ϸ��� ��θ� �����ؾ� �մϴ�. -->
				<li><a href="diaryWrite" class="icon style2 fa-pencil-square-o"><span class="label">�ϱ⾲��</span></a></li>
				<li><a href="searchList" class="icon style2  fa-list-ul"><span class="label">�˻�</span></a></li>
				
				<!-- ���� �׷��� ������ �����Ͽ����ϴ�. jsp�� �ٷ� ȣ���ϰ� �ֽ��ϴ�. ��Ʈ�ѷ��� ���ؼ� �̵��Ϸ��� ��θ� �����ؾ� �մϴ�. -->
				<li><a href="chart" class="icon style2 fa-bar-chart"><span class="label">�����׷���</span></a></li>
			</ul>
		</header>
		
	

<!-- ================ Ref code ================ -->
				<!-- Main -->
				 <table class="w3-table-all" width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
        <!--  <td >��ȣ</td>
         <td >��¥</td>
         <td >����</td>
         <td>÷������</td>
         <td >����</td>
         <td >����ڸ���</td>
         <c:forEach var="article" items="${articleList}">
         <tr>
         <td>${article.num}</td>
         <td>${article.regdate}</td>
         <td>${article.content}</td>
         		  <td>${article.imagename}</td>
         		  <td>${article.emotion}</td>
         		  <td>${article.useremail}</td>
         		<tr>
               </tr></c:forEach> -->
   </table>
<!-- ================ Ref code ================ -->
   
	<!-- Dummy Thumbnails -->
	<section style="display:none">
		<section class="thumbnails">
			<div>
				<!-- <a id="dummy_thumb">
					<img src="/HugHug2/assets/assets_main/images/fulls/06.jpg" style="width:100%;cursor:pointer" onclick="onClickThumb(this)" class="w3-hover-opacity">
					<h3>2018 . 02 . 12 MON</h3>
				</a> -->
			</div>
		</section>
	</section>

	<!-- diary grid section -->
	<section id="main">
		<!-- Thumbnails -->
		<section id="thumbnailImages" class="thumbnails">
			<div id="thumb_container_row1">
				<!-- DUMMY DUMMY DUMMY -->
				<div class="w3-card-2 w3-margin w3-border-0 w3-round w3-center" id="dummy_thumb" style="display:none" onclick="onClickThumb(this)">
					<img class="w3-round" src="/HugHug2/assets/assets_main/images/fulls/06.jpg" style="width:100%;cursor:pointer" class="w3-hover-opacity">
					<h1>2018 . 02 . 12 MON</h1>
					<p style="display:none">Title</p>
					<p style="display:none">Content</p>
					<p style="display:none">Num</p>
				</div>
			</div>
			<div id="thumb_container_row2"></div>
			<div id="thumb_container_row3"></div>
		</section>
	</section>

	<!-- �����ͺ��̽��κ��� �ֱ� �ϱ� 7���� �޾Ƽ� ȭ�鿡 �ѷ��ݴϴ�. -->
		<c:if test="${articleList == null}">
			<div style="text-align:center"><span style="font-size: 20pt">
					�����Ͱ� �����ϴ�. �ϱ⸦ ���ּ���!</span></div>
	</c:if>
	<c:forEach var="article" items="${articleList}">
		<script type="text/javascript">
			console.log(${article.num});
			attachToPage("${article.imagename}", "${article.regdate}", "${article.title}", "${article.content}", "${article.num}");
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
