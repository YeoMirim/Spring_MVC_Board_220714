<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>

<body>
	<h2>글 내용 보기</h2>
	<hr>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
			<form action="modify">
				<tr>
					<td>글 쓴이</td>
					<td><input type="text" name="bname" size="50" value="${dto.bname }"></td>
				</tr>
				<tr>
					<td>글 제목</td>
					<td><input type="text" name="btitle" size="50" value="${dto.btitle }"></td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td>
						<textarea name="bcontent" rows="10" cols="40">${dto.bcontent }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정">
						<!-- <input type="button" value="삭제" onclick="location.href='delete'"> -->
						<a href="delete?bid=${dto.bid }">삭제</a>
						<input type="button" value="목록" onclick="location.href='list'">
					</td>
				</tr>
			</form>
		</table>
		
</body>
</html>