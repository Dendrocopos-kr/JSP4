<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>

<style type="text/css">
.container {
	text-align: center;
}

* {
	font-family: 'Noto Sans KR', sans-serif;
}

*:focus {
	outline: none;
}

.btn {
	background-color: #f5d1ca;
	text-align: center;
	padding: 5px;
	color: #58585a;
	border: none;
	border-radius: 10px;
	font-weight: bold;
	font-size: 1.2em;
	width: 120px;
}

</style>
</head>
<body>
<div class="container">
		<div style="margin: 20px; text-align: right;">
		<a href="/Board/List"><button class="btn">돌아가기</button></a>
	</div>
	<div>${msg}</div>
	<div>
		<form action="/Member_confirm" method="post">
			<div>
				<label>비밀번호 확인:<input type="password" name="pw" placeholder="비밀번호"></label>
			</div>
		<div style="margin: 20px; text-align: right;">
				<input class="btn" type="submit" value="확인">
			</div>
		</form>
	</div>
	</div>
</body>
</html>