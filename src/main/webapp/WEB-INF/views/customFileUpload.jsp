<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<style>
.attachForm {
	width: 800px;
	margin: 0 auto;
}

.button_items {
	text-align: right;
}
</style>
<body>

	<div class="attachForm">
		<h1>곡 업로드하기</h1>
		<form method="POST" action="customeUpload"
			enctype="multipart/form-data" id="frm">



			<select class="form-select" aria-label="Default select example"
				id="select_song_status" name="select_song_status">

				<option value="official">공식</option>
				<option value="custome">비공식</option>

			</select> <select class="form-select" aria-label="Default select example"
				id="songGenre" name="songGenre">

				<option value="ポップス">jpop</option>
				<option value="キッズ">kids</option>
				<option value="アニメ">애니메이션</option>
				<option value="ボーカロイド™曲">보컬로이드</option>
				<option value="ゲームミュージック">게임뮤직</option>
				<option value="バラエティ">버라이어티</option>
				<option value="クラシック">클래식</option>
				<option value="ナムコオリジナル">남코오리지널</option>

			</select>

			<div class="input-group mb-3">
				<input type="text" class="form-control" placeholder="곡이름"
					aria-label="Username" name="songName" value=""
					aria-describedby="basic-addon1" id="songName">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">샘플음원</span> <input
					type="file" class="form-control" name="songData" id="songData">

			</div>
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">채보</span> <input
					type="file" class="form-control" name="musicSheet" id="musicSheet">

			</div>

			<div class="button_items">
				<button type="button" onclick="uploadFnc()"
					class="btn btn-secondary">업로드</button>
			</div>
		</form>
	</div>



</body>
<script>
	function uploadFnc() {

		var songData = document.getElementById("songData").value;
		var songName = document.getElementById("songName").value;
		var songData = document.getElementById("songData").value;
		var musicSheet = document.getElementById("musicSheet").value;
		// 태고의달인 음원파일의 확장자를 구한다. ( fileType )
		var fileLength = songData.length;
		var fileDot = songData.lastIndexOf(".");
		var fileType = songData.substring(fileDot + 1, fileLength)
				.toLowerCase();

		// 태고의달인 채보파일의 확장자를 구한다. ( fileType2 ) 
		var fileLength2 = musicSheet.length;
		var fileDot2 = musicSheet.lastIndexOf(".");
		var fileType2 = musicSheet.substring(fileDot2 + 1, fileLength2)
				.toLowerCase();

		if (songName == null || songName == "") {
			alert("업로드 하시려는 곡의 이름을 입력해주세요!");
			return false;
		}

		if (songData == null || songData == "") {
			alert("업로드하시려는 곡을 첨부해주세요!");
			return false;
		}

		if (musicSheet == null || musicSheet == "") {
			alert("채보를 첨부해주세요!")
			return false;
		}
		console.log("현재 음원파일 확장자 : " + fileType)
		if (fileType != "ogg" && fileType != "mp3") {
			alert("ogg 나 mp3 혹은 wav 파일을 첨부해주세요!");
			return false;
		}
		console.log("현재 채보파일 확장자  : " + fileType2)
		if (fileType2 != "zip" && fileType2 != "tja") {
			alert("채보파일은 zip 혹은 tja 파일을 올려주세요!");
			return false;
		}

		document.getElementById("frm").submit();
	}
</script>
</html>