<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글목록</title>
</head>
<body>
	<h2>게시판 글 목록</h2>
	<hr>
	<table border="1" cellspacing="0" cellpadding="0" width="800">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th width="400">제목</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${boardList }" var="bDto">
			<tr>
				<td>${bDto.bnum }</td>
				<td>${bDto.bname }</td>
				<td>
					<a href="contentView?bnum=${bDto.bnum }">${bDto.btitle }</a>
				</td>
				<td>${bDto.bdate }</td>
				<td>${bDto.bhit }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
				<input type="button" value="글쓰기" onclick="javascript:window.location.href='write'">
			</td>
		</tr>		
	</table>
</body>
</html>