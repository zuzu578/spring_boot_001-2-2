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
.button{
	text-align:right;
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
	 
			
<form id="upload-file-form">
  <div class="input-group mb-3">
		  <input type="text" id="nickName" value="" class="form-control" placeholder="닉네임" aria-label="Username">
</div>

  <input id="inputGroupFile04"class="form-control"  type="file" name="uploadfile" accept="*" />
 <div class="input-group">
		  <span class="input-group-text">댓글을 입력해보세요!</span>
		  <textarea class="form-control" id="comment" value="" aria-label="With textarea"></textarea>
</div>
<div class="button">
<button type="button" onclick="uploadFile();" class="btn btn-primary">작성</button>
</div>
</form>
		
	</div>



</body>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

function uploadFile() {
	const nickName = document.getElementById("nickName").value;
	const comment = document.getElementById("comment").value;
	if(!nickName){
		alert("닉네임을 입력해주세요.")
		return 
	}

	if(!comment){
		alert("댓글을 입력해주세요.")
		return 
	}
	  $.ajax({
	    url: "/uploadFreeBoardFile",
	    type: "POST",
	    data: new FormData($("#upload-file-form")[0]),
	    enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    success: function () {
	     	console.log('success')
	      $.ajax({
	    	  url:"/freeBoard",
	    	  type:"POST",
	    	  data: {"nickName":nickName,"comment":comment},
	    	  success:function(){
	    		  console.log("글자 데이터 성공")
	    	  }
	      })
	      
	    },
	    error: function () {
	      console.log('error')
	    }
	  });
	}
/* const writeComment = () => {
	const nickName = document.getElementById("nickName").value;
	const comment = document.getElementById("comment").value;
	const file = $("input[name='file']");
	console.log(file[0].files);
	if(!nickName){
		alert("닉네임을 입력해주세요.")
		return 
	}

	if(!comment){
		alert("댓글을 입력해주세요.")
		return 
	}
		
		 const form = new FormData();
			 form.append("nickName",nickName);
			 form.append("comment",comment);
			 form.append("file",file);
	         jQuery.ajax({
	             url : "/writeFreeBoard"
	           , type : "POST"
	           , processData : false
	           , contentType : false
	           , enctype: 'multipart/form-data'
	           , data : form
	           , success:function(response) {
	               alert("성공하였습니다.");
	               console.log(response);
	           }
	           ,error: function (jqXHR) 
	           { 
	               alert(jqXHR.responseText); 
	           }
	       });
     
	         
	         
	
} */



</script>
</html>