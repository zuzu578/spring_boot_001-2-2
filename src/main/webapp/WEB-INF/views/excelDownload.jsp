<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<style>
.excelForm{
	width:800px;
	margin:0 auto;

	margin-top:100px;
	
}
h1{
text-align:center;}
.buttons{
text-align:right;
margin-top:50px;
}
</style>
<body>

<div class="excelForm">
        <h1>수록곡 엑셀 다운로드 </h1>
        <form action="/excelDownload" method="get">
        <select class="form-select" aria-label="Default select example" name="genre">
		  <option value="all">전체데이터 다운</option>
		  <option value="ナムコオリジナル">남코오리지널</option>
		  <option value="ボーカロイド™曲">보컬로이드</option>
		  <option value="クラシック">클래식</option>
		  <option value="アニメ">애니메이션</option>
		  <option value="バラエティ">버라이어티</option>
		  <option value="キッズ">키즈</option>
		  <option value="ゲームミュージック">게임뮤직</option>
		  <option value="ポップス"> jpop </option> 
		</select>
		<div class="buttons">
            <button type="submit" class="btn btn-primary">엑셀 다운</button>
        </div>
        </form>
    </div>

</body>
</html>