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
				<input type="hidden" name="bid" value="${dto.bid }"> <!-- bid를 숨김 처리해서 화면에는 보이기 않지만 값을 넘겨줄 수 있음 -->
				<tr>
					<td>글 쓴이</td>
					<td><input type="text" name="bname" size="50" value="${dto.bname }"></td> 
				</tr> <!-- input값을 주석처리 후 ${dto.bname }으로 받으면 이름 수정 안되게 할 수 있음 -->
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
						<input type="button" value="답변" onclick="location.href='reply_view?bid='+${dto.bid }">  <!-- 댓글기능 추가 (기존 글 내용 + 내가 쓸 내용의 폼) -->
						<input type="submit" value="수정"> <!-- modify 요청이 들어감, 해당 글의 번호만 넘겨줘야함 -->
						<input type="button" value="삭제" onclick="location.href='delete?bid='+${dto.bid }">  <!-- 표현식으로 삭제할 글의 bid값을 줌 --> 
						<!-- <a href="delete?bid=${dto.bid }">삭제</a> -->  <!-- bid 값이 날아가야 해당 글이 삭제됨  -->
						<input type="button" value="목록" onclick="location.href='list'"> <!-- 요청만 들어갈때는 onclick 기능 사용 -->
					</td>
				</tr>
			</form>
		</table>
		
</body>
</html>