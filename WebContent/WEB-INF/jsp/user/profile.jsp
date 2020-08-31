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

.grid {
	display: grid;
	grid-gap: 5px;
	position: relative;
	background: lightblue;
	padding: 20px;
	grid-template-columns: auto auto auto auto;
	grid-template-rows: auto auto auto auto;
}
</style>
</head>
<body>
	<div class="container">
		<h1>프로필</h1>
		<div style="margin: 20px; text-align: right;">
			<form action="/Board/List">
				<button type="submit" class="btn">돌아가기</button>
			</form>
		</div>
		<form action="/Profile" method="post" enctype="multipart/form-data" name="profile_frm">
			<div class="grid">
				<div></div>
				<div></div>
				<div></div>
				<div></div>
				<div>프로필이미지</div>
				<div>
					<c:choose>
						<c:when test="${user.profile_img != null}">
						<img alt="" src="/img/user/${login_user.i_user}/${user.profile_img}" style="border-radius: 50%; width: 96px; height: 96px;">
						</c:when>
						<c:otherwise>
							<img alt="" src="/img/default_profile.png">
						</c:otherwise>
					</c:choose>
				</div>

				<div>
					<label>프로필 사진 변경 <br> <input type="file" name="profile_img" accept="image/*">
					</label>
				</div>
				<div>
					<button class="btn" onclick="profile_frm.submit();">업로드하기</button>
				</div>
				<div>ID</div>
				<div>${user.user_id }</div>
				<div> </div>
				<div></div>
				<div>이름</div>
				<div>${user.user_nm }</div>
				<div><span>변경할 이름: </span> <input type="text"> </div>
				<div></div>
				<div>이메일</div>
				<div>${user.user_email }</div>
				<div><span>변경할 이메일: </span> <input type="email"> </div>
				<div></div>
				<div>가입일시</div>
				<div>${user.r_dt }</div>
				<div></div>
				<div></div>
			</div>
		</form>
	</div>
</body>
</html>