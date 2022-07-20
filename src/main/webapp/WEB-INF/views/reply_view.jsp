<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>
</head>
<body>
	
	<h2>댓글 작성</h2>
	<hr>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
	
			<form action="reply">
				<!-- 원글의 group, step, indent를 숨겨서 표현 -->
				<input type="hidden" name="bgroup" value="${dto.bgroup }" >
				<input type="hidden" name="bstep" value="${dto.bstep }" >
				<input type="hidden" name="bindent" value="${dto.bindent }" >
				
				<tr>
					<td>번호</td>
					<td><input type="text" name="bid" size="50" value="${dto.bid }"></td> 
				</tr> 
				<tr>
					<td>조회수</td>
					<td><input type="text" name="bhit" size="50" value="${dto.bhit }"></td> 
				</tr> 
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="bname" size="50" value="${dto.bname }"></td> 
				</tr> <!-- input값을 주석처리 후 ${dto.bname }으로 받으면 이름 수정 안되게 할 수 있음 -->
				<tr>
					<td>제목</td>
					<td><input type="text" name="btitle" size="50" value="[답변] ${dto.btitle }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent" rows="10" cols="40">${dto.bcontent }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="답변쓰기">  <!-- 댓글기능 추가 (기존 글 내용 + 내가 쓸 내용의 폼) -->
						<input type="button" value="목록" onclick="location.href='list'"> <!-- 요청만 들어갈때는 onclick 기능 사용 -->
					</td>
				</tr>
			</form>
		</table>
	
</body>
</html>