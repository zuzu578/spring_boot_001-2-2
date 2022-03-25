<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> tetestset</h1>
${songName } 와(과) 관련된 게시물 ${count }개를 발견하였습니다.
<br/>
<c:forEach var="getWikiResult" items="${getWikiResult}">
<a href="/searchSongWiki?songName=${getWikiResult.songName}">${getWikiResult.songName}</a></br>
</c:forEach>

</body>
</html>