<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
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
</style>
<body>

	<h1 class="song_nName">${songName }</h1>



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
	


</body>
</html>