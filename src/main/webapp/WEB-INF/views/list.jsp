<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>

	<h2>게시판 글 목록</h2>
	<hr>
	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<tr align="center" bgcolor="">
			<td>번호</td>
			<td>글쓴이</td>
			<td width="400">제목</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		
		<!-- arrayList(배열)로 들어온 걸 개수만큼 for문을 돌려서 값 들을 빼냄, items는 BListCommand.java model의 이름-->
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.bid }</td>
				<td>${dto.bname }</td>
				<td>
					<c:forEach begin="1" end="${dto.bindent }"> &nbsp; &nbsp; </c:forEach> <!-- 댓글의 bindent값에 따라서 띄어쓰기 -->
					<a href="content_view?bid=${dto.bid }">${dto.btitle }</a> 
				</td>
				<td>${dto.bdate }</td>
				<td>${dto.bhit }</td>
			</tr>
		</c:forEach>
		
		<tr align="right">
			<td colspan="5"><input type="button" value="글작성" onclick="location.href='write_form'"></td>
		</tr>
	</table>
	
</body>
</html>