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
*{
margin:0;
padding:0;
}
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

.commentArea{
	border-radius:10px;
	padding:10px;
	background-color:white;
	
}
.boardCommentBox{
margin-top:50px;
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
<div class="input-group mb-3">
		  <input type="password" id="password" value="" class="form-control" placeholder="비밀번호" aria-label="Username">
</div>

  <input id="inputGroupFile04"class="form-control" value="" type="file" name="uploadfile" accept="*" />
 <div class="input-group">
		  <span class="input-group-text">댓글을 입력해보세요!</span>
		  <textarea class="form-control" id="comment" value="" aria-label="With textarea"></textarea>
</div>
<div class="button">
<button type="button" onclick="uploadFile();" class="btn btn-primary">작성</button>
</div>
</form>




<div class="commentArea">


</div>
		
</div>



</body>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


<script>
let nowPage = 1;
let isAbleToUpload = true;
document.getElementById('inputGroupFile04').onchange = function () {
	const fileTargetSize = document.getElementById("inputGroupFile04").files[0].size;
	const maxSize = 100*1024*1024;
	if(fileTargetSize > maxSize){
		alert("100mb 이하의 파일만 업로드 가능합니다.");
		isAbleToUpload = false;
		console.log(isAbleToUpload);
		return;
	}
};
	




const getFreeBoardData = () => {
	$.ajax({
		url:"/freeBoard",
		type:"GET",
		data:{"nowPage":nowPage},
		success: function(data){
			console.log(data);
			var commentList = data.boardList; 
			var tr = '<div> </div>';
			$.each(commentList , function(i){ 
				if(commentList[i].boardComment == null){
					commentList[i].boardComment = '';
				}
				if(commentList[i].fileName == null){
					commentList[i].fileName = '';
				}
				if(commentList[i].filePath == null){
					commentList[i].filePath = '';
				}
				// 파일 확장자 체크 
				const fileChk = commentList[i].fileName.slice(commentList[i].fileName.indexOf(".") + 1).toLowerCase();
				console.log(fileChk)
				let tag = '';
				if(fileChk =="jpeg" || fileChk =="jpg" || fileChk =="png" || fileChk =="gif" || fileChk =="bmp"){
					console.log('이미지 파일')
					tag = '<img src="'+commentList[i].filePath+"" +"/"+ ""+commentList[i].fileName+'">'
				}
				if(fileChk.includes('mp4')){
					console.log('영상파일')
					tag = '<video controls width="700"><source src="'+commentList[i].filePath+"" +"/"+ ""+commentList[i].fileName+'" type="video/mp4"> </video>'
				}
				
				console.log(commentList[i].filePath , commentList[i].fileName)
				
				tr += '<div class="boardCommentBox"><div> 닉네임 :' + commentList[i].nickName + '<button onclick=deleteFnc('+commentList[i].idx+') type="button" class="btn btn-danger">X</button></div><div>' + commentList[i].dateTime + '</div><div>' + commentList[i].boardComment + '</div><div>'+tag+'</div></div>'
				; }); 
			$(".commentArea").append(tr); 
		},
		error: function(){
			console.log('error');
		}
	})
}
document.addEventListener("DOMContentLoaded", function(){
	getFreeBoardData();
});


const uploadFile = () =>{
	const nickName = document.getElementById("nickName").value;
	const comment = document.getElementById("comment").value;
	const password = document.getElementById("password").value;
	if(!nickName){
		alert("닉네임을 입력해주세요.")
		return 
	}

	if(!comment){
		alert("댓글을 입력해주세요.")
		return 
	}
	
	if(!password){
		alert("비밀번호를 입력해주세요.");
		return 
	}
	if(!isAbleToUpload){
		alert("파일은 100 mb 이하로 첨부해주세요.");
		return 
	}
	// 댓글작성 
	 $.ajax({
   	  url:"/freeBoard",
   	  type:"POST",
   	  data: {"nickName":nickName,"comment":comment,"password":password},
   	  success:function(){
   		  console.log("글자 데이터 성공")
   		getFreeBoardData();
   	  },
   	  error:function(){
   		  alert("잠시후에 다시 시도해주세요.")
   	  }
     })
     // 이미지첨부 
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
    		     	setTimeout(() => {
    					getFreeBoardData();
    				}, 200) 
    				
    		    },
    		    error: function () {
    		     alert("댓글작성에 실패했습니다. 다시 시도해주세요.");
    		    }
    		  });

		
		$('.commentArea').html('')
		$('#nickName').val('')
		$('#comment').val('')
		$('#password').val('')
		$('#inputGroupFile04').val('')
		
	  	
	
	  
	}

const deleteFnc = (idx) => {
	const password = prompt('비밀번호를 입력해주세요.');
	 $.ajax({
	   	  url:"/deleteFreeBoard",
	   	  type:"DELETE",
	   	  data: {"idx":idx,"password":password},
	   	  success:function(){
	   		  alert('삭제되었습니다.')
	   	  },
	   	  error:function(){
	   		  alert("비밀번호가 일치하지 않습니다.")
	   		  return
	   	  }
	     })
	     $('.commentArea').html('')
	     setTimeout(() => {
			getFreeBoardData();
		}, 20)
}

window.onscroll = function(ev) {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        
    	nowPage++;
    	getFreeBoardData()
        //alert(nowPage);
    	console.log("event detected!",nowPage);
    }
};





</script>
</html>