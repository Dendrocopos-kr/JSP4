<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

.grid {
	display: grid;
	grid-gap: 5px;
	position: relative;
	background: lightblue;
	padding: 20px;
	grid-template-columns: auto auto auto; 
	grid-template-rows: auto auto auto;
}
</style>
</head>
<body>
	<div class="container">
		<h1>프로필</h1>
		<div class="grid">
			<div>프로필이미지</div>
			<div><img src="${user.profile_img == null ? '/img/default_profile.png' : user.profile_img}" style="border-radius: 50%"></div>
			<div></div>
			<div>ID</div>
			<div>${user.user_id }</div>
			<div></div>
			<div>이름</div>
			<div>${user.user_nm }</div>
			<div></div>
			<div>이메일</div>
			<div>${user.user_email }</div>
			<div></div>
			<div>가입일시</div>
			<div>${user.r_dt }</div>
			<div></div>
		</div>
	</div>
</body>
</html>