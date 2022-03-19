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
background-color:black;
color:white;
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
	
	background-color:black;
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
	background-color:black;
	height:auto;
	
}
.boardCommentBox{
margin-top:50px;
}
.feeling_div {
  display: flex;
  justify-content: left;
  align-items: center;
} 
.button-container {
  margin-left: 20px;
}
.feeling_a {
  background-color: #fff;
  border: 2px solid #2199e8;
  padding: 10px 20px;
  border-radius: 2px;
  color: #2199e8;
}     
.active {
  background-color: #2199e8;
  color: #fff;
}
.replyCommentElement{
	border:1px solid red;
}
.commentsTemplate{
margin-top:20px;
}
.replyButton{
text-align:right;
}
.banner_images{
text-align:center;
margin-bottom:100px}
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
	 <div class="banner_images">
			<div class="items">
				<img src="https://taiko-ch.net/images/common/bg_header_chara.png">
			</div>
		
		</div>
			
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
				const replyCommentsList = (callback) =>{
					$.ajax({
					       type: "post",
				           url: "/getFreeBoardReplyComments",
				           data:{idx:commentList[i].idx} ,
				           //async :false,
				           success: function(data){
				              callback(data);
				           },
				           error: function(){
				   			console.log('error');
				   		}
				     });
					
				}
				if(commentList[i].boardComment == null){
					commentList[i].boardComment = '';
				}
				if(commentList[i].fileName == null){
					commentList[i].fileName = '';
				}
				if(commentList[i].filePath == null){
					commentList[i].filePath = '';
				}
				if(commentList[i].commentNickName == null){
					commentList[i].commentNickName = '';
				}
				
				// 파일 확장자 체크 
				const fileChk = commentList[i].fileName.slice(commentList[i].fileName.indexOf(".") + 1).toLowerCase();
				console.log(fileChk)
				let tag = '';
				//let replyComment = '<div class="replyComment"><div class="replyCommentElement">'+commentDepth+'<small>'+replies+'</small>'+commentSeperator+'<small>'+commentList[i].replyComment+''+commentList[i].replyCommentDateTime +'</div></small></div>'
				if(fileChk =="jpeg" || fileChk =="jpg" || fileChk =="png" || fileChk =="gif" || fileChk =="bmp"){
					console.log('이미지 파일')
					tag = '<img src="'+commentList[i].filePath+"" +"/"+ ""+commentList[i].fileName+'">'
				}
				if(fileChk.includes('mp4')){
					console.log('영상파일')
					tag = '<video controls width="700"><source src="'+commentList[i].filePath+"" +"/"+ ""+commentList[i].fileName+'" type="video/mp4"> </video>'
				}
				
				console.log(commentList[i].filePath , commentList[i].fileName)
				replyCommentsList(function(data){
					
					let found = '';
					data.map((i,v)=>{
						found = commentList.find(element => element.idx === i.boardIdx)
						console.log('data====>',data)
					})
					let replies = '';
					// 댓글
					let commentsTemplate = '<div> <input type="text" class="form-control" placeholder="닉네임" id="replyNickName" aria-describedby="basic-addon1">  </div> <div><input type="password" class="form-control" placeholder="비밀번호" id="replyPassWord" aria-describedby="basic-addon1"><div> <textarea class="form-control" id="replyContents" value=""></textarea> </div><div class="replyButton"><button type="button" onclick="writeReplyCancel()" class="btn btn-danger">취소</button><button onclick=writeReplies('+commentList[i].idx+') type="button" class="btn btn-success">댓글작성</button></div> </div>';
					if(found){
						for(let i = 0 ; i < data.length ; i++){
							console.log('댓글 내놔 ' , data[i])
							replies += '<div> ㄴ ' + data[i].replyCommentNickName + ':'+data[i].replyComment +' '+data[i].dateTime +'</div>'
						}
					}
						tr += '<div class="boardCommentBox"><div> 닉네임 :' + commentList[i].nickName + '<button onclick=deleteFnc('+commentList[i].idx+') type="button" class="btn btn-danger">X</button></div><div>' + commentList[i].dateTime + '</div><div>' + commentList[i].boardComment + '</div><div>'+tag+'</div><div>'+replies+' </div><div class="commentsTemplate">'+commentsTemplate+'  </div><button onclick="doWriteReply()" type="button" class="btn btn-light">댓글작성하기</button></div>'
						$(".commentArea").append(tr); 
						$(".commentsTemplate").hide();
					
				})
				}); 
			 
		
	
		},
		error: function(){
			console.log('error');
		}
	})
}
document.addEventListener("DOMContentLoaded", function(){
	getFreeBoardData();
});

const doWriteReply = () => {
	//alert("test")
	$(".commentsTemplate").show();
	//$(".commentsTemplate").hide();
	
}

const writeReplyCancel = () => {
	$(".commentsTemplate").hide();
}

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
   	  },
   	  error:function(){
   		  alert("잠시후에 다시 시도해주세요.")
   	  }
     })
     // 이미지첨부 / 영상첨부 
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
		setTimeout(() => {
			getFreeBoardData();
		}, 3000)  
		
	}

const deleteFnc = (idx) => {
	const password = prompt('비밀번호를 입력해주세요.');
	 $.ajax({
	   	  url:"/deleteFreeBoard",
	   	  type:"DELETE",
	   	  data: {"idx":idx,"password":password},
	   	  success:function(){
	   		  alert('삭제되었습니다.')
	   		  window.location.reload()
	   	  },
	   	  error:function(){
	   		  alert("비밀번호가 일치하지 않습니다.")
	   		  window.location.reload()
	   		  return
	   	  }
	     })
	     //$('.commentArea').html('')
	    /*  setTimeout(() => {
			getFreeBoardData();
		}, 20)  */
		
}

const writeReplies = (idx) =>{
	const commentUserNickName = document.getElementById("replyNickName").value;
	const commentUserPassword = document.getElementById("replyPassWord").value;
	const commentUserContents = document.getElementById("replyContents").value;
	console.log(commentUserNickName,commentUserPassword,commentUserContents)
	if(!commentUserNickName){
		alert('닉네임을 입력해주세요.');
		return 
	}
	if(!commentUserPassword){
		alert('비밀번호를 입력해주세요.');
		return 
	}
	if(!commentUserContents){
		alert('댓글을 입력해주세요.');
		return 
	}
	 
	  $.ajax({
		  url:"/writeReply",
	   	  type:"POST",
	   	  data: {"idx":idx,"nickName":commentUserNickName,"password":commentUserPassword,"contents":commentUserContents},
	   	  success:function(){
	   		$('.commentArea').html('')
	   		getFreeBoardData()
	   	  },
	   	  error:function(){
	   		  alert("댓글작성 실패")
	   	  }
     })
	
	
	
}

/* window.onscroll = function(ev) {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        
    	nowPage++;
    	getFreeBoardData()
        //alert(nowPage);
    	console.log("event detected!",nowPage);
    }
}; */





</script>
</html>