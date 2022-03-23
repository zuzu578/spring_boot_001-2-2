<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
<style>
.ck.ck-editor {
	max-width: 10000px;
}

.ck-editor__editable {
	min-height: 300px;
}

.button_item {
	text-align: right;
}

.docForm {
	width: 800px;
	margin: 0 auto;
}
</style>
<body>
	<div class="docForm">
		<form action="editWiki" method="post" enctype="multipart/form-data" id="frm">
			
	
			<c:forEach var="getWikiResult" items="${getWikiResult}">
			<h3>${getWikiResult.songName } 편집하기</h3>
	<input type = hidden id="idx"  name="idx" value=${getWikiResult.songNo }>
	
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">노래제목</span> <input
					type="text" name="songName" id="songName" value="${getWikiResult.songName } "class="form-control"
					placeholder="" aria-label="Username"
					aria-describedby="basic-addon1">
			</div>
			<select class="form-select" aria-label="Default select example"
				id="songGenre" name="songGenre">
				<option value="">장르</option>
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
				<span class="input-group-text"  id="basic-addon1">부가설명</span> <input
					type="text" value="${getWikiResult.songDescription2}" class="form-control" id="song_description2"
					name="song_description2" placeholder="" aria-label="Username"
					aria-describedby="basic-addon1">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">작곡가</span> <input
					type="text" class="form-control" value="${getWikiResult.songWriter }" id="songWriter" name="songWriter"
					placeholder="" aria-label="Username"
					aria-describedby="basic-addon1">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">채보제작자 </span> <input
					type="text" class="form-control" id="musicSheetWriter"
					name="musicSheetWriter" placeholder="" aria-label="Username"
					aria-describedby="basic-addon1">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">수록 버전 </span> <input
					type="text" class="form-control" id="musicVersion" value="${getWikiResult.songRecordVersion }"
					name="musicVersion" placeholder="" aria-label="Username"
					aria-describedby="basic-addon1">
			</div>
			<select class="form-select" aria-label="Default select example"
				id="kantanLevel" name="kantanLevel">
				<option selected>칸탄 레벨</option>
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
				<option value="6">★★★★★★</option>
				<option value="7">★★★★★★★</option>
				<option value="8">★★★★★★★★</option>
				<option value="9">★★★★★★★★★</option>
				<option value="10">★★★★★★★★★★</option>
				<option value="">없음</option>
			</select> <select class="form-select" aria-label="Default select example"
				id="hutsuuLevel" name="hutsuuLevel">
				<option selected>후츠우 레벨</option>
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
				<option value="6">★★★★★★</option>
				<option value="7">★★★★★★★</option>
				<option value="8">★★★★★★★★</option>
				<option value="9">★★★★★★★★★</option>
				<option value="10">★★★★★★★★★★</option>
				<option value="">없음</option>
			</select> <select class="form-select" aria-label="Default select example"
				id="muzukashiLevel" name="muzukashiLevel">
				<option selected>무즈카시 레벨</option>
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
				<option value="6">★★★★★★</option>
				<option value="7">★★★★★★★</option>
				<option value="8">★★★★★★★★</option>
				<option value="9">★★★★★★★★★</option>
				<option value="10">★★★★★★★★★★</option>
				<option value="">없음</option>
			</select> <select class="form-select" aria-label="Default select example"
				id="oniLevel" name="oniLevel">
				<option selected>오니 레벨</option>
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
				<option value="6">★★★★★★</option>
				<option value="7">★★★★★★★</option>
				<option value="8">★★★★★★★★</option>
				<option value="9">★★★★★★★★★</option>
				<option value="10">★★★★★★★★★★</option>
				<option value="">없음</option>
			</select> <select class="form-select" aria-label="Default select example"
				id="uraLevel" name="uraLevel">
				<option selected>우라 레벨</option>
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
				<option value="6">★★★★★★</option>
				<option value="7">★★★★★★★</option>
				<option value="8">★★★★★★★★</option>
				<option value="9">★★★★★★★★★</option>
				<option value="10">★★★★★★★★★★</option>
				<option value="">없음</option>
			</select>


			<textarea id="editor" name="songDescription">
		${getWikiResult.songDescription }
	</textarea>
			<div class="input-group">
				<span class="input-group-text" id="basic-addon1">곡 설명이미지 </span> <input
					type="file" class="form-control" id="songImage" name="songImage"
					aria-describedby="inputGroupFileAddon04" aria-label="Upload">

			</div>

			
			 <h3>youtube url</h3>
			 <textarea class="form-control" placeholder="Leave a comment here" name="youtubeUrl" id="youtubeUrl" style="height: 100px">${getWikiResult.youtubeUrl }</textarea>

			<div class="button_item">
				<button type="submit" class="btn btn-dark">편집</button>
			</div>
		</form>

	</div>
	</c:forEach>
	<script>
	
	ClassicEditor
	.create( document.querySelector( '#editor' ), {
		toolbar: {
			items: [
				'heading',
				'|',
				'fontFamily',
				'fontSize',
				'fontColor',
				'bold',
				'underline',
				'italic',
				'blockQuote',
				'specialCharacters',
				'|',
				'bulletedList',
				'numberedList',
				'indent',
				'outdent',
				'|',
				'insertTable',
				'mediaEmbed',
				'link',
				'imageUpload',
				'undo',
				'redo'
			]
		},
		language: 'ko',
		image: {
			toolbar: [
				'imageTextAlternative',
				'imageStyle:full',
				'imageStyle:side'
			]
		},
		table: {
			contentToolbar: [
				'tableColumn',
				'tableRow',
				'mergeTableCells',
				'tableCellProperties',
				'tableProperties'
			]
		},
		mediaEmbed: {previewsInData: true},
		licenseKey: '',
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( error => {
		console.error( 'Oops, something went wrong!' );
		console.error( 'Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:' );
		
		console.error( error );
	} );
	
	
	
	
    </script>
</body>
</html>