<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
* {
	font-family: 'Noto Sans KR', sans-serif;
}

*:focus {
	outline: none;
}

#container {
	width: 450px;
	margin: 80px auto;
	border-radius: 7%;
	background-color: #faf9f7;
	padding: 20px;
}

h1 {
	margin: 0;
	color: #58585a;
	font-size: 1.8em;
	text-align: center;
	letter-spacing: 3px;
}

#frm {
	margin: 20px;
}

#frm label {
	width: 100px;
	float: left;
	text-align: right;
	margin-right: 25px;
	color: #58585a;
	font-size: 0.9em;
	padding: 6px;
}

#frm div {
	margin: 20px;
}

#frm input {
	width: 200px;
	padding: 7px;
	border: none;
	border-radius: 20px;
	color: #58585a;
	text-indent: 10px;
}

#frm input:hover {
	box-shadow: 1px 1px 1.5px 1px #f2f2f2;
	transition-delay: 0.07s;
	border: none;
}

#frm button {
	width: 100px;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	margin-top: 20px;
	margin-left: 138px;
	font-weight: bold;
}

#login {
	text-decoration: none;
	color: #58585a;
	margin: 20px;
	font-weight: bold;
}

.err {
	color: #ff6f69;
	text-align: left;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<div id="container">
		<h1>회원 가입</h1>
		<div>
			<form id="frm" action="/Join" method="post" onsubmit="return chk()">
					<div><label>아이디</label><input type="text" name="user_id" placeholder="아이디" value="${tempData.getUser_id()}" required></div>
				<div><label>비밀번호</label><input type="password" name="user_pw" placeholder="비밀번호" required></div>
				<div><label>비밀번호 확인</label><input type="password" name="user_pwre" placeholder="비밀번호 확인" required></div>
				<div><label>이름</label><input type="text" name="nm" placeholder="이름" value="${tempData.getUser_nm()}" required></div>
				<div><label>이메일</label><input type="email" name="email" placeholder="이메일" value="${tempData.getUser_email()}"></div>
					<div><button type="submit">SUBMIT</button></div>
				
			</form>
		</div>
		<div id="err">${Err}</div>
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