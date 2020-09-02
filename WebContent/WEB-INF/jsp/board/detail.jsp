<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title}</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<style>
.main_body {
	width: 95%;
	margin: 0 auto;
	display: flex;
}

.main_body textarea {
	resize: none;
	border: none;
	border-radius: 10px;
	background: #FDF2F0;
}

.like {
	background: #EACACB;
	text-align: center;
	border: none;
}

.menu_btn {
	width: 95%;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	font-weight: bold;
	margin-bottom: 20px;
}

.ctnt {
	display: flex;
	min-height: 400px;
	height: auto;
	padding: 0px;
	flex-direction: column;
	justify-content: space-between;
	border-radius: 10px;
	border: #DEB3CF 1px solid;
	margin: 10px;
}

.menu {
	padding: 10px;
	width: 20%;
	text-align: center;
	background: #FADCDA;
	border-radius: 10px;
	margin: 10px;
}

.board_body {
	width: 85%;
	display: flex;
	flex-direction: column;
	align-items: stretch;
}

.cmt {
	margin: 10px;
	text-align: center;
	border-collapse: collapse;
	vertical-align: middle;
	background: lightgrey;
	/* width: 100%; */
}

.cmt_name {
	background: lightgrey;
	width: 15%;
}

.cmt_body {
	background: lightpink;
	text-align: left;
	width: 60%;
}

.cmt_dt {
	background: lightgrey;
	font-size: 0.8em;
	width: 15%;
}

.err {
	color: red;
	font-weight: bold;
}

.cmtFrm {
	margin: 10px;
	border: 1px black solid;
}

.board_ctnt_title, .board_profile {
	justify-content: space-between;
	background: #EACACB;
	display: flex;
	margin-bottom: 30px;
	border-radius: 10px;
	padding: 10px;
	margin: 10px;
}

.profile {
	justify-content: space-between;
	background: #EACACB;
	margin-bottom: 30px;
	border-radius: 10px;
	padding: 10px;
	margin: 10px;
	display: inline-block;
}

.menu_info {
	padding: 30px;
}

hr {
	border: white 2px solid;
	margin-bottom: 30px;
}

.material-icons {
	font-family: 'Material Icons';
	font-weight: normal;
	font-style: normal;
	font-size: 24px; /* Preferred icon size */
	display: inline-block;
	line-height: 1;
	text-transform: none;
	letter-spacing: normal;
	word-wrap: normal;
	white-space: nowrap;
	direction: ltr;
	color: red;
	/* Support for all WebKit browsers. */
	-webkit-font-smoothing: antialiased;
	/* Support for Safari and Chrome. */
	text-rendering: optimizeLegibility;
	/* Support for Firefox. */
	-moz-osx-font-smoothing: grayscale;
	/* Support for IE. */
	font-feature-settings: 'liga';
}

.like:hover {
	cursor: pointer;
}

tr:first-child>td:first-child {
	border-radius: 10px 0px 0px 0px;
}

tr:first-child>td:last-child {
	border-radius: 0px 10px 0px 0px;
}

tr:last-child>td:first-child {
	border-radius: 0px 0px 0px 10px;
}

tr:last-child>td:last-child {
	border-radius: 0px 0px 10px 0px;
}

div input {
	width: 100px;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	font-weight: bold;
	margin: 10px;
}

#cmtFrm {
	display: flex;
	justify-content: space-between;
}

#cmt_body {
	width: 80%;
	padding: 7px;
	border: 0;
	border-bottom: 2px solid #58585a;
	text-indent: 10px;
	font-weight: bold;
}

#cmt_submit {
	box-shadow: 0px 1px 1px black;
}

.profile_img {
	border-radius: 50%;
	vertical-align: middle;
	font-size: 3em;
	overflow: hidden;
}

.profile_img img {
	width: 1em;
	height: 1em;
	padding: 10px;
}

.highlight {
	color: red;
	font-weight: bold;
}
</style>
<body>
	<div class="main_body">
		<div class="menu">
			<div class="menu_info">
				<B>게시판 메뉴</B>
			</div>
			<hr>
			<!-- 
			<div>
				게시글 번호 :${data.i_board}
				</div>
			 -->
			<div>${temp }</div>
			<div>
				<a href="List?page=${param.page == null ? 1 : param.page}&record_cnt=${param.record_cnt == null ? 10 : param.record_cnt}&searchText=${param.searchText}&searchType=${param.searchType == null ? 1 : param.searchType}">
					<button class="menu_btn">리스트보기</button>
				</a>
			</div>
			<c:if test="${ data.i_user == login_user.i_user }">
				<div>
					<form action="Del" id="delfrm" method="post">
						<input type="hidden" name="id" value="${data.i_board}"> <a href="#" onclick="submitDel()"><button class="menu_btn">삭제</button></a>
					</form>
				</div>
				<div>
					<a href="Regmod?id=${data.i_board}"><button class="menu_btn">수정</button></a>
				</div>
			</c:if>
		</div>
		<div class="board_body">
			<div class="board_ctnt_title">
				<div class="title">
					<span>제목 :</span><span id="elTitle">${data.title}</span><span class="err">${err}</span>
				</div>
			</div>
			<div class="board_profile">
				<div>작성일 : ${data.r_dt}</div>
				<c:if test="${data.like == 1}">
					<!-- 내/외부 처리용 action -->
					<!-- <form action="" id="like" method="post"> -->
					<form action="Like" id="like" method="post">
						<input type="hidden" name="like" value="0"> <input type="hidden" name="id" value="${data.i_board}"> <input type="hidden" name="searchText" value="${param.searchText}"> <input type="hidden" name="record_cnt" value="${param.record_cnt == null ? 10 : param.record_cnt}"> <input type="hidden" name="page" value="${param.page == null ? 1 : param.page}"> <a href="?id=${data.i_board}&like=0"> <input type="hidden" name="searchType" value="${param.searchType == null ? '1' : param.searchType}">
							<button class="like">
								<span class="material-icons"> favorite</span><sup> ${data.like_count } </sup>
							</button>
						</a>
					</form>
				</c:if>
				<c:if test="${data.like == 0}">
					<!--<form action="" id="like" method="post"> -->
					<form action="Like" id="like" method="post">
						<input type="hidden" name="like" value="1"> <input type="hidden" name="id" value="${data.i_board}"> <input type="hidden" name="searchText" value="${param.searchText}"> <input type="hidden" name="record_cnt" value="${param.record_cnt == null ? 10 : param.record_cnt}"> <input type="hidden" name="page" value="${param.page == null ? 1 : param.page}"> <a href="?id=${data.i_board}&like=1"> <input type="hidden" name="searchType" value="${param.searchType == null ? '1' : param.searchType}">
							<button class="like">
								<span class="material-icons"> favorite_border</span><sup> ${data.like_count } </sup>
							</button>
						</a>
					</form>
				</c:if>
				<div>조회수 : ${data.hits}</div>
			</div>
			<div class="ctnt">
				<pre style="margin: 10px;" id="elCtnt">${data.ctnt}</pre>
			</div>
			<table class="profile">
				<tr>
					<td class="profile_img">
						<c:if test="${data.user_profile_img==null}">
							<img src="/img/default_profile.png">
						</c:if>
						<c:if test="${data.user_profile_img!=null}">
							<img src="/img/user/${data.i_user}/${data.user_profile_img}">
						</c:if>
					</td>
					<td>
						<span>작성자 :</span> <span id="elNm">${data.user_nm}</span>
					</td>
				</tr>
			</table>
			<table class="cmt">
				<c:if test="${!empty cmtData}">
					<c:forEach items="${cmtData}" var="item">
						<tr>
							<c:if test="${item.is_del == 0}">
								<td>
									<div class="profile_img">
										<c:if test="${data.user_profile_img==null}">
											<img src="/img/default_profile.png">
										</c:if>
										<c:if test="${data.user_profile_img!=null}">
											<img src="/img/user/${data.i_user}/${data.user_profile_img}">
										</c:if>
									</div>
								</td>
								<td class="cmt_name">${item.user_nm}</td>
								<td class="cmt_body">${item.cmt}</td>
							</c:if>
							<c:if test="${item.is_del == 1}">
								<td class="cmt_name">【비공개】</td>
								<td class="cmt_body">삭제된 댓글입니다.</td>
							</c:if>
							<td class="cmt_dt">${item.r_dt}</td>
							<td class="info">
								<c:if test="${item.is_del == 0}">
									<c:if test="${ item.i_user == login_user.i_user }">
										<button onclick="regCmt(${item.i_cmt},'${item.cmt}')">수정</button>
										<button onclick="cmtDel(${item.i_cmt});">삭제</button>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty cmtData}">
					<tr>
						<td colspan=5 style="text-align: center;">작성된 댓글이 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<div>
				<form action="cmt" id="cmtFrm" method="post">
					<input type="hidden" name="i_cmt" value="0"> <input type="hidden" name="id" value="${data.i_board}"> <input type="text" id="cmt_body" name="cmt" placeholder="댓글내용"> <input type="submit" id="cmtSubmt" value="댓글 달기"> <input type="hidden" id="cmtCancelBtn" value="취소" onclick="cmtCancel();">
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function submitDel() {
			event.preventDefault();
			var result = confirm('삭제하시겠습니까?');
			if (result) {
				delfrm.submit();
			}
		}
		
		function cmtDel(cmt) {
			event.preventDefault();
			var result = confirm('댓글을 삭제하시겠습니까?');
			if (result) {
				location.href = 'cmt?id=${data.i_board}&i_cmt='+cmt;
			}
		}
		function cmtCancel(){
			cmtFrm.i_cmt.value = 0;
			cmtFrm.cmt_body.value = '';
			cmtFrm.cmtSubmt.value = '댓글 달기';
			cmtFrm.cmtCancelBtn.type = 'hidden';
		}
		function regCmt(cmtPK,cmt){
			cmtFrm.i_cmt.value = cmtPK;
			cmtFrm.cmt_body.value = cmt;
			cmtFrm.cmtSubmt.value = '댓글 수정';
			cmtFrm.cmtCancelBtn.type = 'button';
			cmtFrm.cmt_body.focus();
		}
		
		function doHighlight(){
			let searchText = '${param.searchText}';
			let searchType = '${param.searchType}';
			let title = elTitle.innerText;
			let ctnt = elCtnt.innerText;
			let nm = elNm.innerText;
			switch(searchType){
			case '1':
				title = title.replace( new RegExp('${param.searchText}','gi'), "<span class=\"highlight\">" + searchText + "</span>");
				elTitle.innerHTML = title;
				break;
			case '2':
				ctnt = ctnt.replace(new RegExp('${param.searchText}','gi'), "<span class=\"highlight\">" + searchText + "</span>");
				elCtnt.innerHTML = ctnt;
				break;
			case '3':
				title = title.replace(new RegExp('${param.searchText}','gi'), "<span class=\"highlight\">" + searchText + "</span>");
				elTitle.innerHTML = title;
				ctnt = ctnt.replace(new RegExp('${param.searchText}','gi'), "<span class=\"highlight\">" + searchText + "</span>");
				elCtnt.innerHTML = ctnt;
				break;
			case '4':
				nm = nm.replace(new RegExp('${param.searchText}','gi'), "<span class=\"highlight\">" + searchText + "</span>");
				elNm.innerHTML = nm;
				break;
			}		
		}
		
		if(${param.searchText == '' ? false : true}){
			doHighlight();
		}
	</script>
</body>
</html>