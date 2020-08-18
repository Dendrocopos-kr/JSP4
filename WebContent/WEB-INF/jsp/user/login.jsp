<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
	#Msg{
	color:red;
	font-weight: bold;
	}
</style>
</head>
<body>
	<h1>로그인</h1>
	<div id="Msg">${Msg}</div>
	<div id="container">
		<form id="frm" action="/Login" method="post" onsubmit="return chk()">
			<div>
				<input type="text" name="user_id" placeholder="ID" required="required" value="${id}">
			</div>
			<div>
				<input type="password" name="user_pw" placeholder="PW" required="required">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
		</form>
		<a href="/Join">회원가입</a>
	</div>
	<script type="text/javascript">
		function chk() {
			if (frm.user_id.value == 0) {
				alert('아이디를 입력해주세요');
				frm.user_id.focus();
				return false;
			}
			if (frm.user_pw.value == 0) {
				alert('비밀번호를 입력해주세요');
				frm.user_pw.focus();
				return false;
			}
		}
	</script>
</body>
</html>