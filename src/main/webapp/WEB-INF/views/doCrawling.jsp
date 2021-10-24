<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style>
.title {
	text-align: center;
}

.form {
	width: 700px;
	margin: 0 auto;
}

.subtitle {
	text-align: center;
}
</style>
<body>
	<h1 class="title">최신곡 업데이트</h1>

	<div class="form">
		<h3 class="subtitle">업데이트 하고싶은 곡의 장르를 선택해주세요</h3>
		<form method="post" action="startCrawling">
			<div class="input-group">
				<select class="form-select" id="inputGroupSelect04"
					aria-label="Example select with button addon" name="crawlingGenre"
					class="crawlingGenre">

					<option value="pops">jpop</option>
					<option value="kids">kids</option>
					<option value="anime">애니메이션</option>
					<option value="vocaloid">보컬로이드</option>
					<option value="game">게임뮤직</option>
					<option value="variety">버라이어티</option>
					<option value="classic">클래식</option>
					<option value="namco">남코오리지널</option>
				</select>
				<button class="btn btn-outline-secondary" type="submit">업데이트</button>
			</div>
		</form>
	</div>
</body>
</html>