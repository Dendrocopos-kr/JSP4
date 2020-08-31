<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.koreait.pjt.vo.BoardVO"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>게시판</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

*:focus {
	outline: none;
}

a button {
	color: #58585a;
	text-decoration: none;
}

#user_welcome {
	text-align: right;
	margin: 20px;
}

#logout {
	background-color: #f5d1ca;
	text-align: center;
	padding: 5px;
	color: #58585a;
	border: none;
	border-radius: 10px;
	font-weight: bold;
}

#write {
	width: 150px;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	font-weight: bold;
	font-size: 1.5em;
}

table {
	border: black 1px solid;
	border-collapse: collapse;
	margin: 20px;
}

th, td {
	padding: 1em;
	text-align: center;
	color: black;
	border: none;
	border-bottom: grey solid 1px;
}

th {
	background: #F19393;
	color: white;
	border-bottom: 1px black solid;
}

td:nth-child(2) {
	text-align: left;
}

.itemRow:hover {
	background: #F2F2F2;
	cursor: pointer;
	color: black;
}

.container {
	text-align: center;
	margin: 30px auto;
	width: 95%;
}

.container h1 {
	text-align: center;
}

#usr-color {
	color: #ef9173;
	font-weight: bold;
}

.pageBtn {
	padding: 3px 9px;
	margin: 0 1px 0 2px;
	color: black;
	cursor: pointer;
	text-decoration: none;
	border: 1px solid rgba(0, 0, 0, 0);
}

.pageBtn:hover {
	color: blue;
	font-weight: bold;
	border: 1px solid black;
	border-radius: 5px;
}

.curpage {
	padding: 3px 9px;
	margin: 0 1px 0 2px;
	text-decoration: none;
	border: 1px solid rgba(0, 0, 0, 0);
	color: grey;
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
	width: 100px;
}

#selFrm {
	text-align: right;
}
</style>
<body>
	<div class="container">
		<div>
			<h1>
				<span class="material-icons"> event_note </span>게시판 리스트
			</h1>
		</div>
		<div id="user_welcome">
			✔【<span id="user-color">${login_user.user_nm}</span>】님 환영합니다. 
		</div>
		<div  style="text-align: right; margin: 20px;">
		<a href="/Profile"><button class="btn">프로필</button></a>			
			<a href="/Logout">
				<button class="btn">로그아웃</button>
			</a>
		</div>
		<div style="text-align: right; margin: 20px;">
			<a href="Regmod">
				<button id="write">
					<span class="material-icons"> create </span>새글 쓰기
				</button>
			</a>

		</div>
		<div>
			<form id="selFrm" action="List" method="get" style="margin: 20px;">
				<input type="hidden" name="page" value="${param.page == null ? 1 : param.page }"> <span>페이지당 표시할 글 수</span> 
				<input type="hidden" name="searchText" value="${param.searchText}">
				<select name="record_cnt" onchange="changerecordCnt()">
					<c:forEach begin="10" end="50" step="10" var="item">
						<c:choose>
							<c:when test="${param.record_cnt == item}">
								<option value="${item}" selected="selected">${item}개</option>
							</c:when>
							<c:otherwise>
								<option value="${item }">${item}개</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</form>
		</div>
		<table class="table">
			<tr>
				<th style="width: 10%;">번호</th>
				<th style="width: 50%;">제목</th>
				<th style="width: 15%;">조회수</th>
				<th style="width: 15%;">작성자</th>
				<th style="width: 15%;">작성일</th>
			</tr>
			<c:if test="${!empty data}">
				<c:forEach items="${data}" var="item" varStatus="status">
					<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
						<td>${item.i_board }</td>
						<td>${item.title }</td>
						<td>${item.hits }</td>
						<td>
							<c:if test="${item.user_profile_img==null}">
								<span class="material-icons" style="border-radius: 50%; vertical-align: middle; font-size: 3em;"> account_circle </span>
							</c:if>
							<c:if test="${item.user_profile_img!=null}">
								<img src="/img/user/${item.i_user}/${item.user_profile_img}" width="48px;" height="48px;" style="border-radius: 50%; vertical-align: middle;">
							</c:if>
							<span>${item.user_nm }</span>
						</td>
						<td>${item.r_dt }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty data}">
				<tr>
					<td colspan=5 style="text-align: center;">작성글이 없습니다.</td>
				</tr>
			</c:if>
		</table>

		<div style="margin: 20px;">
			<form action="/Board/List">
				<span>검색어 입력: <input name="searchText" value="${param.searchText}"></span>
				<button type="submit" class="btn">검색</button>
			</form>
		</div>
		<div style="margin: 20px;">
			<c:forEach var="cnt" begin="1" end="${paging}" step="1">
				<c:if test="${currentPage != cnt}">
					<a href="List?page=${cnt}&record_cnt=${param.record_cnt == null ? 10 : param.record_cnt}&searchText=${param.searchText}" class="pageBtn">${cnt}</a>
				</c:if>
				<c:if test="${currentPage == cnt}">
					<span class="curpage">${cnt}</span>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
	function moveToDetail(PK) {
		console.log(PK);
		location.href = 'Detail?id='+PK+'&page=${param.page == null ? 1 : param.page}&record_cnt=${param.record_cnt == null ? 10 : param.record_cnt}&searchText=${param.searchText}';
	}
	function changerecordCnt(){
		selFrm.submit();
	}
	</script>
</body>
</html>