<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page session="false"%>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Home</title>
</head>
<style>
* {
	padding: 0;
	margin: 0;
}

.banner_images {
	margin-top: 80px;
	text-align: center;
}

.find {
	width: 1000px;
	margin: 0 auto;
}

#searchResult {
	width: 1000px;
	margin: 0 auto;
}

#result {
	width: 1000px;
	margin: 0 auto;
}

.genre {
	display: flex;
	flex-wrap: wrap;
}

.images {
	cursor: pointer;
}

.song_upper_title {
	text-align: center;
	width: 500px;
	margin: 0 auto;
}

.desc_for_title {
	text-align: center;
	font-weight: bold;
	font-size: 20px;
}

.select_items {
	width: 400px;
}

.items {
	text-align: center;
	margin-top: 60px;
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
}
</style>
<body>
	<input type="hidden" name="getGenre" id="getGenre" value="">
	<input type="hidden" name="getStatus" id="getStatus" value="">


	<input type="hidden" name="nowSongTrack" id="nowSongTrack" value=""
		class="nowSongTrack">

	<nav class="nav_items">
		<div class="nav_items">
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
				<a href="/" style="color: white; text-decoration: none"> 메인</a>
			</p>
			<p class="para">
				<a href="/freeTalkBoard" style="color: white; text-decoration: none"> 자유게시판</a>
			</p>
		</div>


	</nav>

	<div id="root">

		<form name="hidden_area">
			<input type="hidden" name="check_status" id="check_status" value="">

		</form>

		<div class="banner_images">
			<div class="items">
				<img src="https://taiko-ch.net/images/common/bg_header_chara.png">
			</div>
			<img
				src="https://taiko.namco-ch.net/taiko/images/songlist/tit_songlist.png">

		</div>

		<div class="find">
			<div class="input-group mb-3">
				<input type="text" class="form-control" aria-label="Username"
					id="searchKeyword" value="" name="searchKeyword"
					aria-describedby="basic-addon1">
				<button type="button" @click="searchByText()" class="btn btn-dark">검색</button>
			</div>
			<!--  -->
			<div class="select_items">
				<select class="form-select" aria-label="Default select example"
					id="searchSelect">
					<option value="official">공식수록곡</option>
					<option value="custome">비공식 수록곡</option>


				</select>
			</div>


			<div class="desc">
				<p class="desc_for_title">장르별 곡전체보기</p>
			</div>
			<div class="genre">
				<div class="images" @click="getDataByGenre('ポップス')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_pops.png">
				</div>
				<div class="images" @click="getDataByGenre('キッズ')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_kids.png">
				</div>
				<div class="images" @click="getDataByGenre('アニメ')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_anime.png">
				</div>
				<div class="images" @click="getDataByGenre('ボーカロイド™曲')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_vocalo.png">
				</div>
				<div class="images" @click="getDataByGenre('ゲームミュージック')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_game.png">
				</div>
				<div class="images" @click="getDataByGenre('バラエティ')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_variety.png">
				</div>
				<div class="images" @click="getDataByGenre('クラシック')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_classic.png">
				</div>
				<div class="images" @click="getDataByGenre('ナムコオリジナル')">
					<img
						src="https://taiko.namco-ch.net/taiko/images/songlist/btn_namco.png">
				</div>


			</div>
		</div>



		<div id="result">
			<table class="table">

				<thead>
					<tr>
						<th scope='col'>장르</th>
						<th scope='col'>곡이름</th>
						<th scope='col'>공식 여부</th>
						<th scope='col'>음원파일</th>
						<th scope='col'>play</th>
						<th scope='col'>pause</th>


					</tr>
				</thead>

				<tbody id='bidders'>
					<tr v-for="(dataList, idx) in newSearchList" :key="idx">
						<td>{{dataList.SONG_GENRE}}</td>
						<td>{{dataList.SONG_NAME}}</td>
						<td>{{dataList.SONG_TYPE}}</td>
						<td v-if="dataList.FILE_NAME != null">
							<button type="button"
								@click="fileDown(dataList.FILE_NAME,dataList.FILE_PATH)"
								class="btn btn-dark">download</button>
						</td>


						<td v-else>
							<div v-if="dataList.SONG_TYPE == 'custome'">
								<a href="/customFileUpload">비공식업로드하기 </a>
							</div>
							<div v-else>
								<a :href="'fileUpload?songNo=' + dataList.SONG_NO" style="">
									공식업로드하기 </a>
							</div>
						</td>

						<td v-if="dataList.FILE_NAME != null">

							<button type="button" class="btn btn-light" id="button"
								@click="start(dataList.FILE_PATH,dataList.SAMPLE_SONG,idx);"
								value="PLAY">play</button>
						</td>
						<td v-if="dataList.FILE_NAME != null">
							<button type="button" id="button" class="btn btn-light"
								@click="pause(idx);" value="PAUSE">PAUSE</button> <!--일시정지-->


						</td>
						<!--  
						<td>{{dataList.FILE_PATH}}{{dataList.FILE_NAME}}{{dataList.SAMPLE_SONG}}</td> 
						-->



					</tr>

				</tbody>
			</table>
		</div>

		<div id="searchResult">
			<table class="table">

				<thead>
					<tr>
						<th scope='col'>장르</th>
						<th scope='col'>곡이름</th>
						<th scope='col'>공식 여부</th>
						<th scope='col'>음원파일</th>
						<th scope='col'>play</th>
						<th scope='col'>pause</th>

					</tr>
				</thead>

				<tbody id='bidders2'>
					<tr v-for="(dataList, idx) in newSearchList" :key="idx">
						<td>{{dataList.SONG_GENRE}}</td>
						<td>{{dataList.SONG_NAME}}</td>
						<td>{{dataList.SONG_TYPE}}</td>
						<td v-if="dataList.FILE_NAME != null">
							<button type="button"
								@click="fileDown(dataList.FILE_NAME,dataList.FILE_PATH)"
								class="btn btn-dark">download</button>
						</td>

						<td v-else>
							<div v-if="dataList.SONG_TYPE == 'custome'">
								<a href="/customFileUpload">비공식업로드하기 </a>
							</div>
							<div v-else>
								<a :href="'fileUpload?songNo=' + dataList.SONG_NO" style="">
									공식업로드하기 </a>
							</div>
						</td>
						<td v-if="dataList.FILE_NAME != null">

							<button type="button" id="button" class="btn btn-light"
								@click="start(dataList.FILE_PATH,dataList.SAMPLE_SONG,idx);"
								value="PLAY">play</button>
						</td>
						<td v-if="dataList.FILE_NAME != null">
							<button type="button" id="button" class="btn btn-light"
								@click="pause(idx);" value="PAUSE">PAUSE</button> <!--일시정지-->


						</td>
						<!--  
						<td>{{dataList.FILE_PATH}}{{dataList.FILE_NAME}}{{dataList.SAMPLE_SONG}}</td> 
						-->


					</tr>




				</tbody>
			</table>
		</div>







	</div>


</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/howler/2.2.1/howler.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<script>
/*


 window.onscroll = function() {

	    // @var int totalPageHeight
	    var totalPageHeight = document.body.scrollHeight; 

	    // @var int scrollPoint
	    var scrollPoint = window.scrollY + window.innerHeight;

	    // check if we hit the bottom of the page
	    if(scrollPoint >= totalPageHeight)
	    {
	    	nowPage++;
	    	//alert(nowPage);
	    	getList(nowPage)
	    }
	}
 */
 //var nowPage = 1;  //페이징과 같은 방식이라고 생각하면 된다. 
 	var nowPage = 1;  //페이징과 같은 방식이라고 생각하면 된다. 	
function getDataList(nowPage){
	var nowPage = nowPage;
	var genre = document.getElementById("getGenre").value;
	var searchSelect = document.getElementById("getStatus").value;
    var newSearchList = []; 
	var test = 0;
	
	
    $.ajax({
    url:'/getDataBaseSong', //request 보낼 서버의 경로
    type:'get', // 메소드(get, post, put 등)
     async:false, 
    data:{'genre':genre,'searchSelect':searchSelect,'nowPage':nowPage}, //보낼 데이터
    success: function(data) {
    	//alert("success");
    	 //var arr = JSON.parse(data);a
    	 //console.log(data);
    	 newSearchList = data;
    },
    error: function(err) {
        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
    }
});
    for(var i = 0 ; i < newSearchList.length; i ++){
    	
    //console.log(newSearchList[i]);
    }
	
	return newSearchList;



	

	
}
</script>



<script>

let prevPlay ="";
let nextPlay ="";
let url = "";
let key = "";
let key2 = "";
var nowPlaying_track = "";
var audio = null; 

const app = new Vue({
    el: '#root',
    data: {
    	songList:[],
    	searchList:[],
    	newSearchList:[],
    	
    	
    	
 	 
    },
    created(){
    	
    	window.addEventListener('scroll', this.handleScroll);	
    },
    mounted(){
    	//alert(newSearchList);
    	// 초기 검색 결과 테이블 hide!
    	document.getElementById("result").style.display = "none";
    	document.getElementById("searchResult").style.display = "none";

    	
    
    	
    
    },
    destroyed(){
    	window.removeEventListener('scroll', this.handleScroll);
    },
    methods: {
    	
    	fileDown(fileName,filePath){
    		var fileName = fileName;
    		var filePath = filePath;
    		location.href="/songDownLoad?fileName="+fileName+"&filePath="+filePath+"";

    		
    		
    	},
		// 스크롤 이벤트 시 무한 스크롤 페이징 !
    	handleScroll (event) {
    	
    		var getList = function(){
    			
    			//console.log("getList!!!! => ");
    			
    			return getDataList(nowPage); 
    			
    			
    		}
    		
    		  // @var int totalPageHeight
    	    var totalPageHeight = document.body.scrollHeight; 

    	    // @var int scrollPoint
    	    var scrollPoint = window.scrollY + window.innerHeight;

    	    // check if we hit the bottom of the page
    	    if(scrollPoint >= totalPageHeight)
    	    {
    	    	nowPage++;
    	    	//alert(nowPage);
    	    	console.log("find Function");
    	    	//getList();
    	    	//this.songList.push(songList);
    	    	console.log("음악 리스트!! => " + getDataList(nowPage).length);
    	    	var newSearchList= []
    	    	for(var i = 0 ; i<getDataList(nowPage).length ; i++ ){
    	    		
    	    	this.newSearchList.push(getDataList(nowPage)[i]);
    	    	}
    	    	console.log('newsearch => ' ,this.newSearchList);
    	    }
    	      
    	},
    	
    
    	    

    	// 미리듣기 중단 버튼 
		/**/
		pause(idx){
			
			if(key == idx){
				audio.pause();	
				
    		
			}
			
			
		},
    	// 미리듣기 시작버튼 
    		// 다른 노래버튼을 클릭하 현재 재생중인노래를 멈추고 다른노래를 재생한다. 
    	 	start(fileName,filePath,idx){
			
			if(audio != null){
				audio.pause(); 
			}
			var nowSongTrack = $("#nowSongTrack").val(idx);
			nowPlaying_track = $("#nowSongTrack").val();
			
			
			console.log("idx Result! -----> " + idx);
			console.log("key =======> " + key);
			
			
			key = idx; 
    		fileName = fileName;
        	filePath = filePath; 
       	  //  alert("노래를 재생합니다! ");
       	    url = fileName+filePath;
       	    audio =  new Audio(url); 
    	    audio.play();
       	    console.log('audio'); 
       	  
    	
    		}, 
    		
    	
    	searchByText(){
    			// 무한 스크롤 데이터 저장 배열 초기화 
    		//this.newSearchList = []; 
    		document.getElementById("result").style.display = "none";
    		$("#bidders2").empty();
    		let searchKeyword = $("#searchKeyword").val();
    		
    		let searchSongType = $("#searchSelect").val();
    		
    		var searchSelect = document.getElementById("searchSelect").value; 
    		
    		if(!searchKeyword){
    			
    			alert("곡이름을 입력해주세요!");
    			
    			return false;
    		}
    		//alert(searchKeyword);
    		axios.get('/searchSongs',{
    			params:{
    				searchSongType : searchSongType,
    				searchKeyword : searchKeyword,
    			}
    		})  
    		.then((res)=>{
    		 	console.log(res.data);
    			
    			// 검색후 기존테이블을 숨기고 결과를 보여줍니다.
    			//console.log('fesf',this.searchList);
    			for(var i = 0 ; i<Object.keys(res.data).length;i++){
    				this.newSearchList.push(res.data[i]);
    			}
    			document.getElementById("searchResult").style.display = "block";
    		
    		})
    		
    		
    		
    	},
    	getDataByGenre(genre){
    		// 무한 스크롤 데이터 저장 배열 초기화 
    	//	this.newSearchList = []; 
    		document.getElementById("result").style.display = "block";
    		// 기존에 있는 데이터를 초기화 한다!
    		$("#bidders").empty();
    	    //alert("테이블 초기화! ")
    		
    		var genre = genre;
    		var searchSelect = document.getElementById("searchSelect").value;
    		$("#getGenre").val(genre);
    		$("#getStatus").val(searchSelect);
    		//alert(searchSelect);
    		
 
    		axios.get('/getDataBaseSong',{
    			params:{
    				genre : genre,
    				searchSelect: searchSelect,
    			}
    		})  
    	    .then((res)=>{
    	    	
    	    	//console.log('res => ' , res.data);
    	    	for(var i = 0 ; i < Object.keys(res.data).length ; i ++){
    	    		//console.log(res.data[i]);
    	    	//	console.log(res.data[i]);
    	    		this.newSearchList.push(res.data[i]);
    	    		
    	  	
    	    	} 
    	    	//document.getElementById("result").style.display = "block";
    	    	  
    	    	//console.log("songlist!!!@#!@#!@#!@#!@# => "+  this.songList);
    	    	
    	    	
    	    		//$("#title").text(genre);
    	    	document.getElementById("searchResult").style.display = "none";
    	    	
    	    })  
			
        }
    }
});
</script>

</html>