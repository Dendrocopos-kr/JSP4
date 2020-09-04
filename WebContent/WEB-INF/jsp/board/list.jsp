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
	margin: 20px auto;
	min-width: 100%;
}

th, td {
	padding: 1em;
	text-align: center;
	color: black;
	border: none;
	border-bottom: grey solid 1px;
	padding: 5px;
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

.profile {
	text-align: center;
	border-radius: 50%;
	vertical-align: middle;
	font-size: 3em;
	overflow: hidden;
}

.profile img {
	border-radius: 50%;
	width: 1em;
	height: 1em;
}

.like_color {
	color: red;
}

.highlight {
	color: red;
	font-weight: bold;
}

#likeListContainer {
	border: 1px solid darkgray;
	position: absolute;
	left: 0px;
	top: 30px;
	width: AUTO;
	height: AUTO;
	max-width: 400px;
	overflow-y: auto;
	background: white;
	opacity: 0;
	z-index: -1;
	transition-duration: 500ms;
	display: flex;
	padding: 10px;
	border-radius: 10px;
	box-shadow: 5px 5px 10px black;
}

.like_user {
	width: 48px;
	height: 48px;
	border-radius: 50%;
	overflow: hidden;
}

.nm {
	font-size: 0.5em;
	margin: 5px;
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
		<div style="text-align: right; margin: 20px;">
			<a href="/Member_confirm"><button class="btn">프로필</button></a> <a href="/Logout">
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
				<input type="hidden" name="page" value="${param.page == null ? 1 : param.page }"> <span>페이지당 표시할 글 수</span> <input type="hidden" name="searchText" value="${param.searchText}"> <input type="hidden" name="searchType" value="${searchType == null ? '1' : searchType}">
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
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th></th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:if test="${!empty data}">
				<c:forEach items="${data}" var="item" varStatus="status">
					<tr class="itemRow">
						<td onclick="moveToDetail(${item.i_board})">${item.i_board }</td>
						<td onclick="moveToDetail(${item.i_board})">${item.title }
							<small>( ${item.board_cmt_cnt} )</small>
						</td>
						<td>${item.hits }</td>
						<td onclick="getlikeList(${item.i_board},${item.board_like_cnt})">
							<div>
								<c:if test="${item.my_like == 1}">
									<div class="material-icons like_color">favorite</div>
									<div>${item.board_like_cnt > 0 ? item.board_like_cnt : '' }</div>
								</c:if>
								<c:if test="${item.my_like == 0}">
									<div class="material-icons like_color">favorite_border</div>
									<div>${item.board_like_cnt > 0 ? item.board_like_cnt : ''}</div>
								</c:if>
							</div>
						</td>
						<td>
							<div class="profile">
								<c:if test="${item.user_profile_img==null}">
									<img src="/img/default_profile.png">
								</c:if>
								<c:if test="${item.user_profile_img!=null}">
									<img src="/img/user/${item.i_user}/${item.user_profile_img}">
								</c:if>
							</div>
						</td>
						<td>
							<span>${item.user_nm }</span>
						</td>
						<td>${item.r_dt }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty data}">
				<tr>
					<td colspan=7 style="text-align: center;">작성글이 없습니다.</td>
				</tr>
			</c:if>
		</table>

		<div style="margin: 20px;">
			<form action="/Board/List">
				<select name="searchType">
					<option value="1" ${searchType == '1' ? 'selected' : '' }>제목</option>
					<option value="2" ${searchType == '2' ? 'selected' : '' }>내용</option>
					<option value="3" ${searchType == '3' ? 'selected' : '' }>제목+내용</option>
					<option value="4" ${searchType == '4' ? 'selected' : '' }>작성자</option>
				</select>
				<span>검색어 입력: <input name="searchText" value="${param.searchText}"></span>
				<button type="submit" class="btn">검색</button>
			</form>
		</div>
		<div style="margin: 20px;">
			<c:forEach var="cnt" begin="1" end="${paging}" step="1">
				<c:if test="${currentPage != cnt}">
					<a href="List?page=${cnt}&record_cnt=${param.record_cnt == null ? 10 : param.record_cnt}&searchText=${param.searchText}&searchType=${searchType == null ? '1' : param.searchType}" class="pageBtn">${cnt}</a>
				</c:if>
				<c:if test="${currentPage == cnt}">
					<span class="curpage">${cnt}</span>
				</c:if>
			</c:forEach>
		</div>
		<div id="likeListContainer"></div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script type="text/javascript">
	function moveToDetail(i_board) {
		location.href = 'Detail?id='+i_board+'&page=${param.page == null ? 1 : param.page}&record_cnt=${param.record_cnt == null ? 10 : param.record_cnt}&searchText=${param.searchText}&searchType=${searchType == null ? 1 : searchType}';
	}
	function changerecordCnt(){
		selFrm.submit();
	}
	function getlikeList(i_board,like_count){
		if(like_count == 0 ) {return;}

		const target = event.target;
		
		likeListContainer.innerHTML = "";
		likeListContainer.style.opacity = 1;
		likeListContainer.style.zIndex = 10;
		
		likeListContainer.style.left = target.getBoundingClientRect().right + window.pageXOffset;
		likeListContainer.style.top = target.getBoundingClientRect().bottom + window.pageYOffset;
		
		axios.get('/Board/Like2',{
			params:{
				i_board
				}
		}).then(function(res){
			//console.log(res)
			if( res.data.length > 0 ){
				for(let i=0; i<res.data.length; i++){
					const result = makeLikeUser(res.data[i])
					likeListContainer.innerHTML += result ;
				}
			}
		});
	}
	
	function makeLikeUser(item){
		const img = item.user_profile_img == null ?
				`<img  class="like_user" src="/img/default_profile.png">` 
				: 
				`<img class="like_user" src="/img/user/\${item.i_user}/\${item.user_profile_img}">`;
				
		const ele = `<div class="likeItemContainer">
			<div class="profileContainer">
				<div class="profile">
					\${img}
				</div>
			</div>
			<div class="nm">\${item.user_nm}</div>
		</div>
		`
		return ele
	}
	
	</script>
</body>
</html>