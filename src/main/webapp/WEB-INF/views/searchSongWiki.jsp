<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<style>
.songInfoBox {
	width: 800px;
	border: 1px solid gray;
	margin: 0 auto;
	padding: 10px;
}

.area2 {
	text-align: center;
}

.image_area {
	text-align: center;
	
}
.image_area img{
	width:50%;
	
}

.level_title {
	text-align: center;
	background-color: black;
	padding: 2px;
}

.song_nName {
	text-align: center;
}
.MainDescription{
	padding-left:50px;
	padding-right:50px;
	
}
.replyForm{
	width:800px;
	margin:0 auto;
}
#commentArea{
	border:1px solid blue;
	border-radius:10px;
	
	padding:10px;
	margin-top:100px;
	width:800px;
	margin:0 auto;
	

}
.paging{
	text-align:center;
	width:800px;
	margin:0 auto;
	
}
a{
	cursor:pointer;
}
</style>
<body>

	<h1 class="song_nName">
	<c:forEach var="getWikiResult" items="${getWikiResult}">
	${getWikiResult.songName }
	<input type = hidden id="idx" value=${getWikiResult.songNo }>
	</c:forEach>
	<button type="button" onclick="editWiki()" class="btn btn-dark">편집</button>
	</h1>
	



	<div class="songInfoBox">
		<c:forEach var="getWikiResult" items="${getWikiResult}"
			varStatus="status">
			
			<div class="image_area">
				<img src=".${getWikiResult.filePath }/${getWikiResult.fileName}">

			</div>
			<div class="area2">
				<p class="sub2">${getWikiResult.songDescription2 }</p>
				<p class="songName">${getWikiResult.songName }</p>
				<p class="genre">${getWikiResult.songGenre }</p>
			</div>
			<div class="area3">
				<p class="writer">작곡가 : ${getWikiResult.songWriter }</p>
				<p class="musicSheetWriter">채보제작자 : </p>
				<p class="musicVersion">수록된 버전 / 기체:
					${getWikiResult.songRecordVersion }</p>



			</div>
			<div class="level_title">
				<p class="lev_tit" style="color: white; font-weight: bold;">난이도
				</p>
			</div>
			<div class="level_list">
				<p class="level_items">칸탄: ${getWikiResult.kantan }</p>
				<p class="level_items">후츠우: ${getWikiResult.hutsuu }</p>
				<p class="level_items">무즈카시: ${getWikiResult.muzukashi }</p>
				<p class="level_items">오니: ${getWikiResult.oni }</p>
				<p class="level_items">우라: ${getWikiResult.ura }</p>


			</div>


		</c:forEach>


	</div>
	<div class="youtube_frame">
	<c:forEach var="getWikiResult" items="${getWikiResult}">
		${getWikiResult.youtubeUrl }
	</c:forEach>
	
	</div>


	<div class="MainDescription">
		<c:forEach var="getWikiResult" items="${getWikiResult}"
			varStatus="status">
			${getWikiResult.songDescription }  
			
			
			
			</c:forEach>

	</div>
	
	
<div class="replyForm">
  <div class="input-group mb-3">
		  <input type="text" id="nickName" value="" class="form-control" placeholder="닉네임" aria-label="Username">
</div>
<div class="input-group mb-3">
		  <input type="password" id="password" value="" class="form-control" placeholder="비밀번호" aria-label="Username">
</div>
 <div class="input-group">
		  <span class="input-group-text">댓글을 입력해보세요!</span>
		  <textarea class="form-control" id="comment" value="" aria-label="With textarea"></textarea>
</div>
<div class="button">
<button type="button" onclick="doReply();" class="btn btn-primary">작성</button>
</div>
</div>

<div id="commentArea">


</div>
<div class="paging">
<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" onclick=prev() aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
  
    <li class="page-item">
      <a class="page-link" onclick=next() aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

let nowPage = 1;
document.addEventListener("DOMContentLoaded", function(){
	getReply();
});

const getReply = () => {
	const type = 'wiki';
	const idx = document.getElementById("idx").value;
	const form = new FormData();
	form.append('type',type);
	form.append('idx',idx);
	form.append("nowPage",nowPage);
	axios({
		url: '/getReplyComments',
		method: 'post',
		data: form
	})
	.then((res)=>{
		let tr = '';
		console.log('댓글데이터 =>' , res.data)
		if(res.data.length === 0){
			alert('마지막 페이지입니다.')
			return false;
		}
		
		if(res.data.length > 0){
			$("#commentArea").html("");
			res.data.map((item)=>{
				console.log(item)
				tr += '<div class="boardCommentBox"><div class="nickName"> '+item.replyCommentNickName+': '+item.replyComment+' :'+item.dateTime+' </div></div>'
				
			})
			$('#commentArea').append(tr)
		}
		
		
	})
	.catch((e)=>{
		console.log(e.message)
	})
}

const doReply = () => {
	const type = "wiki";
	const idx = document.getElementById("idx").value;
	const nickName = document.getElementById("nickName").value;
	const password = document.getElementById("password").value;
	const comment = document.getElementById("comment").value;
	if(!nickName){
		alert('닉네임을 입력해주세요.')
		return
	}
	
	if(!password){
		alert('비밀번호를 입력해주세요.')
		return
	}
	if(!comment){
		alert('댓글을 입력해주세요.')
		return
	}
	
	const form = new FormData();
	form.append('nickName', nickName);
	form.append('password', password);
	form.append('contents', comment);
	form.append('idx', idx);
	form.append('type',type);
	
	console.log(idx);
	axios({
		url: '/writeReply',
		method: 'post',
		data: form
	})
	.then((res)=>{
		$("#nickName").val("");
		$("#password").val("");
		$("#comment").val("");
		
		$('#commentArea').html('')
		getReply()
	})
	.catch((e)=>{
		console.log(e.message)
	})
	
}


const prev = () => {
	nowPage --
	console.log('nowPage ===>' , nowPage);
	if(nowPage <= 0){
		alert('마지막 페이지 입니다.')
		nowPage = 1;
		return 
	}
	//$("#commentArea").html("");
	getReply();
	
	
}


const next = () => {
	nowPage++
	getReply();
}

const editWiki = () => {
	const idx = document.getElementById("idx").value;
	
	window.location.href = '/editSongWiki?idx='+idx+'';
}



</script>
</html>