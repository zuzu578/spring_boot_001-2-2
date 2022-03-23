<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Insert title here</title>
<style>
.nav_items {
	background-color: black;
	color: white;
	padding: 10px;
	display: flex;
}

.para {
	padding: 10px;
	color: white;
}

body {
	background-image:
		url(https://switch.taiko-ch.net/images/special/millionCamp/img_mv.jpg);
	background-repeat: no-repeat;
	background-size: 100%;
}

.search_bar {
	width: 800px;
	margin: 0 auto;
	margin-top: 100px;
}
.wikiInfo{
	width:800px;
	margin:0 auto;
	border-radius:10px;
	
	background-color:white;
}
.recent{
	width:800px;
	margin:0 auto;
	background-color:white;
	margin-top:200px;
	border-radius:10px;
	padding:10px;
	
	
}
.info{
text-align:center;
}
.infomations{padding:10px;
}
</style>
</head>
<body>
	<nav class="nav_items">
		<div class="nav_items">
			<p class="para">
				<a href="/" style="color: white; text-decoration: none"> 메인</a>
			</p>
			<p class="para">
				<a href="/customFileUpload"
					style="color: white; text-decoration: none">채보 업로드하기!</a>
			</p>
			<p class="para">
				<a href="/doCrawling" style="color: white; text-decoration: none">
					태고의달인 최신곡 크롤링하기!</a>
			</p>
			<p class="para">
				<a href="/gowiki" style="color: white; text-decoration: none">
					태고의달인 위키</a>
			</p>
			<p class="para">
				<a href="/makedoc" style="color: white; text-decoration: none">
					위키문서 만들기</a>
			</p>

		</div>


	</nav>
	<div class="search_bar">
		<form action="searchSongWiki" method="get">

			<div class="input-group mb-3">

				<input type="text" class="form-control"
					placeholder="검색하고싶으신 곡을 입력하세요" aria-label="Username"
					aria-describedby="basic-addon1" name="songName" id="songName">
				<button type="submit" class="btn btn-light">검색</button>
			</div>

		</form>
	</div>
	
	<div class="wikiInfo">
		<div class="info"> <h1> 공지 </h1></div>
		
		<div class="infomations">
			<p>안녕하세요. 이 곳은 태고위키입니다.
현재까지 총 문서수 ${count } 입니다.<br/>
타인에게 불쾌감을 주는 미디어 컨텐츠 게시를 금지합니다.<br/>
<strong>최근 음란, 혐오, 차별적 언어 등 반사회적 게시물에 대한 당국의 강도 높은 단속이 이뤄지고 있습니다.<br/> 이용자 여러분도 아시다시피 태고위키도 예외는 아닙니다. 이와 관련된 단어 및 이미지가 발견될 시 관리자 직권으로 해당 문구 삭제 혹은 문서 전체를 삭제할 수 있음을 알려드리며, 문서 작성 시 욕설과 비속어, 비하어 등 사회 정서에 맞지 않는 단어 사용을 지양해 주시기를 간곡히 당부드립니다. 감사합니다</strong>
</p>
		</div>
	</div>
	
	<div class="recent">
	<h2> 최근 작성된 게시물</h2>
	
	</div>





</body>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

document.addEventListener("DOMContentLoaded", function(){
	getRecentWiki();
	
});

const getRecentWiki = () => {
	let tr = '';
	axios.get('/getRecentWiki')
	.then((res)=>{
		console.log(res.data);
		res.data.map((item)=>{
			tr+= '<div><a href=/searchSongWiki?songName='+item.songName+'> '+item.songName+' </a> '+item.dateTime+'<div> </div>'
		})
		
		$(".recent").append(tr);
	})
	.catch((e)=>{
		console.log(e.message);
	})
}



</script>
</html>