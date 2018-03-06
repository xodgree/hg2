<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

글번호 : ${diary.num}<br>	
날짜 : ${diary.regdate} <br>
내용 : ${diary.content} <br>
작성자: ${diary.useremail} <br>
감정 : ${diary.emotion} <br>
이미지이름: ${diary.imagename} <br>


</body>
</html>