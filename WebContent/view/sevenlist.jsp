<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 7개만 받아오는 list -->
<c:if test="${count ==0 }">
   <table class="table-bordered" width="700">
      <tr class="w3-grey">
         <td align="center">게시판에 저장된 글이 없습니다.</td>
   </table>
</c:if>
<c:if test="${count !=0 }">
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
   </table></c:if>