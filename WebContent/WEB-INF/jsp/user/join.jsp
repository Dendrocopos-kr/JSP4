<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<div id="container">
		<div>
			<div id="Err">${Err}</div>
			<form id="frm" action="/Join" method="post" onsubmit="return chk()">
				<div>
					<input type="text" name="user_id" placeholder="아이디" required="required" value="${tempData.getUser_id()}">
				</div>
				<div>
					<input type="password" name="user_pw" placeholder="비밀번호" required="required" value="${tempData.getUser_pw()}">
				</div>
				<div>
					<input type="password" name="user_pwre" placeholder="비밀번호 확인" required="required" value="${tempData.getUser_pw()}">
				</div>
				<div>
					<input type="text" name="nm" placeholder="이름" required="required" value="${tempData.getUser_nm()}">
				</div>
				<div>
					<input type="email" name="email" placeholder="e-mail" value="${tempData.getUser_email()}">
				</div>
				<div>
					<input type="submit" value="회원가입">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function chk() {
			if (frm.user_id.value.length < 5) {
				alert('아이디는 5글자 이상이 되어야합니다.');
				frm.user_id.focus();
				return false;
			}
			if (frm.user_pw.value.length < 8) {
				alert('비밀번호는 8글자 이상이 되어야합니다.');
				frm.user_pw.focus();
				return false;
			}
			if (frm.user_pw.value != frm.user_pwre.value) {
				alert('비밀번호가 같지 않습니다.');
				frm.user_pwre.focus();
				return false;
			}
			if (frm.nm.value.length > 0) {
				const korean = /[^가-힣]/;
				if (korean.test(frm.nm.value)) {
					alert('이름은 한글만 사용해주세요.')
					frm.nm.focus();
					return false;
				}
			}
			if (frm.email.value.length > 0) {
				const email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				if (!email.test(frm.email.value)) {
					alert('이메일 형식에 맞춰서 입력해주세요.')
					frm.eamil.focus();
					return false;
				}
			}
		}
	</script>
</body>
</html>