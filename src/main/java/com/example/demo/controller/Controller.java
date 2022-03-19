package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.Dao;
import com.example.demo.freeBoardDto.FreeBoard;
import com.example.demo.freeBoardDto.FreeBoardReplyCommentDto;
import com.example.demo.wikidto.TaikoWikiDto;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.message.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.taikosong.filedown.TaikoSongDownloadFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
//@RestController

public class Controller {
	private static String BoardFilePath = "/Users/helloworld/git/spring_boot_001-2-5/src/main/webapp/resources/assets/freeBoardImage";
	private static String FILE_SERVER_PATH = "/Users/helloworld/git/springBoot-Transaction/spring_boot_001 2/src/main/webapp";
	private static String FileSavePath = "/Users/helloworld/git/springBoot-Transaction/spring_boot_001 2/src/main/webapp/resources/assets";
	@Autowired
	private Dao dao;

	// jsp MVC pattern
	// 태고의달인 수록곡 메인 화면
	@RequestMapping("/")
	public String testJsp(HttpServletRequest req, Model model) {

		return "test";
	}
	
	@RequestMapping("/freeTalkBoard")
	public String freeTalkBoard(HttpServletRequest req , Model model) {
		
		return "freeTalkBoard";
	}

	/**
	 * 비공식 수록곡 업로드 페이지
	 */
	@RequestMapping("/customFileUpload")
	public String customFileUpload() {

		return "customFileUpload";
	}

	/**
	 * file upload page 로 이동!
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(HttpServletRequest req, Model model) {

		String songNo = req.getParameter("songNo");
		if (songNo == null) {
			songNo = "";

		}
		String songName = dao.selectSongInfo(songNo);

		model.addAttribute("songName", songName);
		model.addAttribute("songNo", songNo);

		return "fileUpload";
	}

	/**
	 * 비공식 , 공식 곡을 업로드 db 에 저장합니다
	 * 
	 */
	@RequestMapping(value = "/customeUpload", method = RequestMethod.POST)
	public String customeUpload(@RequestParam("songData") MultipartFile file,
			@RequestParam("musicSheet") MultipartFile file2, ModelAndView mv, HttpServletRequest req, Model model,
			HttpServletResponse res) throws IllegalStateException, IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter 를 담을 변수!

		String select_song_status = req.getParameter("select_song_status");
		String songGenre = req.getParameter("songGenre");
		String songName = req.getParameter("songName");

		paramMap.put("select_song_status", select_song_status);
		paramMap.put("songGenre", songGenre);
		paramMap.put("songName", songName);

		if (!file.getOriginalFilename().isEmpty() && !file2.getOriginalFilename().isEmpty()) {

			file.transferTo(new File(FileSavePath, file.getOriginalFilename())); // => 샘플곡을 서버에 저장
			file2.transferTo(new File(FileSavePath, file2.getOriginalFilename())); // => 채보zip파일등을 서버에 저장

			String fileName = file.getOriginalFilename(); // song(ogg File)
			String musicSheetFileName = file2.getOriginalFilename(); // musicSheet( zip file ..ect )

			paramMap.put("sampleSongName", fileName); // SAMPLE_SONG
			paramMap.put("musicSheetFileName", musicSheetFileName); // FILE_NAME
			paramMap.put("filePath", "/resources/assets/");

			dao.insertCustomeSongs(paramMap); // 2) TB_TAIKO_SONGS => 곡 이름 , 번호 , 장르 ,타입(공식 ,비공식 여부 )

			dao.insertCustomeSongInfo(paramMap); // 3) TB_TAIKO_FILES => 곡 정보 , 경로

			// model.addAttribute("msg", "File uploaded successfully.");
			PrintWriter out = res.getWriter();

			out.println("<script>alert('success!'); window.location.href = '/'; </script>");

			out.flush();

		} else {
			PrintWriter out = res.getWriter();
			out.println("<script>alert('success!'); window.location.href = '/'; </script>");

			out.flush();

		}

		return null;

	}

	/**
	 * 
	 * 공식 수록곡을 업로드 , db에 저장합니다. (공식 수록곡)
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 * 
	 */
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUpload(@RequestParam("songData") MultipartFile file,
			@RequestParam("musicSheet") MultipartFile file2, ModelAndView mv, HttpServletRequest req, Model model,
			HttpServletResponse res) throws IllegalStateException, IOException {

		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter 를 담을 변수!

		// String uploadSelect = req.getParameter("uploadSelect");
		String select_song_status = req.getParameter("select_song_status");
		String songName = req.getParameter("songName");
		String songNo = req.getParameter("songNo");

		if (songNo == null) {
			songNo = "";
		}

		paramMap.put("select_song_status", select_song_status); // 공식 , 비공식 여부
		paramMap.put("songName", songName); // 음원 이름
		paramMap.put("songNo", songNo); // 해당 수록곡의 번호

		/**
		 * 1) tb_TB_TAIKO_SONGS => 장르 , 공식 비공식여부 , 음원이름 2) tb_tb_taiko_file => 파일경로 , 파일
		 * 이름
		 * 
		 */
		if (!file.getOriginalFilename().isEmpty() && !file2.getOriginalFilename().isEmpty()) {

			file.transferTo(new File(FileSavePath, file.getOriginalFilename())); // songData(샘플 음원을 서버경로에 저장)
			file2.transferTo(new File(FileSavePath, file2.getOriginalFilename())); // musicSheet(채보 파일을 서버경로에 저장)

			String taikoSongName = file.getOriginalFilename(); // 샘플 음원의 원제목을 얻는다.
			String musicSheetFileName = file2.getOriginalFilename(); // 채보 파일의 원제목을 얻는다.
			paramMap.put("taikoSongName", taikoSongName);
			paramMap.put("musicSheetFileName", musicSheetFileName); // FILE_NAME
			paramMap.put("filePath", "/resources/assets/");

			dao.insertSongContents(paramMap); // 2) tb_taiko_file

			// model.addAttribute("msg", "File uploaded successfully.");
			PrintWriter out = res.getWriter();

			out.println("<script>alert('success!'); window.location.href = '/'; </script>");

			out.flush();

		} else {
			PrintWriter out = res.getWriter();
			out.println("<script>alert('success!'); window.location.href = '/'; </script>");

			out.flush();

		}

		return "";

	}

	/**
	 * 곡을 검색합니다.
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/searchSongs", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray searchSongs(HttpServletRequest req) {

		String searchSongType = req.getParameter("searchSongType");
		String searchKeyword = req.getParameter("searchKeyword");

		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter 를 담을 변수!

		paramMap.put("searchSongType", searchSongType);
		paramMap.put("searchKeyword", searchKeyword);

		List<Map<String, Object>> result = dao.searchSongs(paramMap);

		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> map : result) {
			jsonArray.add(convertMapToJson(map));
		}

		// result = dao.searchSongs(paramMap);

		return jsonArray;
	}

	// 카테고리 클릭시,
	// db 에 저장한 태고의달인 공식 / 비공식의 수록곡 이름 , 채보 , 수록곡 음원파일등의 데이터를 가져옵니다.
	@RequestMapping(value = "/getDataBaseSong", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getDataBaseSong(HttpServletRequest req) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		String nowPage = req.getParameter("nowPage");
		String cntPerPage = "20";

		String genre = req.getParameter("genre");
		String searchSelect = req.getParameter("searchSelect");

		paramMap.put("genre", genre);
		paramMap.put("searchSelect", searchSelect); // 공식 , 비공식 여부

		if (nowPage == null) {
			nowPage = "1";

		}
		// 해당 장르의 곡의 갯수를 구합니다.
		int total = dao.songAllCount(paramMap);
		PagingDto pagingResult = new PagingDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		paramMap.put("START", pagingResult.getStart());
		paramMap.put("END", pagingResult.getEnd());

		List<Map<String, Object>> pagination = dao.getPagination(paramMap);

		// List<Map<String, Object>> result = dao.getDataBaseSong(paramMap);
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> map : pagination) {
			jsonArray.add(convertMapToJson(map));
		}

		return jsonArray;
	}

	/**
	 * 태고의달인 최신 수록곡 크롤링페이지
	 */
	@RequestMapping(value = "/doCrawling", method = RequestMethod.GET)
	public String doCrawling() {

		return "doCrawling";
	}

	@RequestMapping("/deleteSongData")
	public String deleteSongData(HttpServletRequest req, HttpServletResponse res) {
		// 삭제할 곡들의 장르를 가져옵니다.
		String deleteGenre = req.getParameter("deleteGenre");
		// 데이터베이스에 있는 기존에 크롤링한 곡을 제거합니다.
		dao.deleteOldSongList();
		return "삭제되었습니다.";
	}

	@RequestMapping("/startCrawling")
	public void startCrawling(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 크롤링할 곡의 장르를 가져옵니다.
		String crawlingGenre = req.getParameter("crawlingGenre");

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crawlingGenre", crawlingGenre);

		Document doc = Jsoup.connect("https://taiko.namco-ch.net/taiko/songlist/" + crawlingGenre + ".php#sgnavi")
				.get();
		Elements rowList = doc.select("table"); // 수록곡을 가져옵니다.
		Elements songGenre = doc.select("h3"); // 가져온 수록곡의 장르를 가져옵니다.

		for (Element row : rowList) {
			Elements cellList = row.select("tr"); // (th)
			// 데이터베이스에 새로운 곡을 저장합니다.
			for (int i = 2; i < cellList.size(); i++) {

				paramMap.put("songGenre", songGenre.text());
				paramMap.put("songList", cellList.get(i).text());

				dao.insertSongs(paramMap);
				// songs.put(i, cellList.get(i).text());
			}
		}

		PrintWriter out = res.getWriter();

		out.println("<script>alert('해당장르의 곡을 크롤링했습니다!'); window.location.href = '/'; </script>");

		out.flush();

	}

	/**
	 * 
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 * 
	 *                     태고의달인 최신 수록곡을 크롤링합니다.
	 */
	@RequestMapping(value = "/getSongList", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, Object> taikoCrawling(HttpServletRequest req, Locale locale, Model model) throws IOException {

		/**
		 * ?ž¥ë¥? 1) namco => 남코오리지널 2) pops => jpop 3) kids => kids 4) anime => anime
		 * 5) vocaloid => 보컬로이드 6) game => 게임뮤직 7) variety => 버라이어티 8) classic => 클래식
		 * 
		 */
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String genre = "namco";

		JSONObject songs = null;
		JSONParser parser = new JSONParser();
		songs = new JSONObject();

		Document doc = Jsoup.connect("https://taiko.namco-ch.net/taiko/songlist/" + genre + ".php#sgnavi").get();
		Elements rowList = doc.select("table"); // 수록곡을 가져옵니다.
		Elements songGenre = doc.select("h3"); // 가져온 수록곡의 장르를 가져옵니다.

		// System.out.println("genre => " + songGenre.text());

		for (Element row : rowList) {
			Elements cellList = row.select("tr"); // (th)
			for (int i = 2; i < cellList.size(); i++) {
				// db 에 수록곡을 저장합니다.
				paramMap.put("songGenre", songGenre.text());
				paramMap.put("songList", cellList.get(i).text());

				dao.insertSongs(paramMap);
				songs.put(i, cellList.get(i).text());
			}
		}
		return songs;
	}

	/*
	 * desc : map list data를 jsonObject 로 변환합니다. 그런다음 jsonArray 로 변환합니다.
	 * 
	 * @return jsonObject
	 * 
	 */
	@SuppressWarnings({ "unchecked" })

	public static JSONObject convertMapToJson(Map<String, Object> map) {

		JSONObject json = new JSONObject();

		for (Map.Entry<String, Object> entry : map.entrySet()) {

			String key = entry.getKey();

			Object value = entry.getValue();

			// json.addProperty(key, value);

			json.put(key, value);

		}

		return json;

	}

	// 수록곡 음원 (ogg file) 다운로드!
	// ex) // => /Users/helloworld/git/springBoot-Transaction/spring_boot_001
	// 2/src/main/webapp/resources/assets/エンジェル ドリーム.ogg
	@RequestMapping("/songDownLoad")
	public void songDownLoad(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelAndView mav) {
		String fileName = req.getParameter("fileName");
		String filePath = req.getParameter("filePath");
		TaikoSongDownloadFile fileDown = new TaikoSongDownloadFile(); // 파일다운로드 객체생성
		try {
			fileDown.filDown(req, res, FILE_SERVER_PATH + filePath, fileName, fileName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// 태고의달인 위키 문서 작성페이지로 이동합니다.
	@RequestMapping("/makedoc")
	public String makedoc() {

		return "makedoc";
	}

	// 태고의달인 위키 페이지로 이동합니다.
	@RequestMapping("/gowiki")
	public String gowiki() {

		return "gowiki";
	}
	// 태고의달인 위키 문서를 작성합니다.

	@RequestMapping(value = "/doMakingDoc", method = RequestMethod.POST)
	public String doMakingDoc(@RequestParam("songImage") MultipartFile songImage, ModelAndView mv,
			HttpServletRequest req, Model model, HttpServletResponse res) throws IllegalStateException, IOException {
		HashMap<String, Object> songLevel = new HashMap<String, Object>(); // tb_taiko_song_level 에 넣을 params
		HashMap<String, Object> songInfo = new HashMap<String, Object>(); // tb_taiko_wiki_content에 넣을 params
		HashMap<String, Object> imageParams = new HashMap<String, Object>(); // TB_TAIKO_IMAGE에 넣을 params

		// 곡 정보
		String songName = req.getParameter("songName");
		String songDescription = req.getParameter("songDescription");
		String song_description2 = req.getParameter("song_description2");
		String songWriter = req.getParameter("songWriter");
		String musicSheetWriter = req.getParameter("musicSheetWriter");
		String musicVersion = req.getParameter("musicVersion");
		String songGenre = req.getParameter("songGenre");
		String youtubeUrl = req.getParameter("youtubeUrl");
		songInfo.put("songName", songName);
		songInfo.put("songDescription", songDescription);
		songInfo.put("song_description2", song_description2);
		songInfo.put("songWriter", songWriter);
		songInfo.put("musicSheetWriter", musicSheetWriter);
		songInfo.put("musicVersion", musicVersion);
		songInfo.put("songGenre", songGenre);
		songInfo.put("youtubeUrl", youtubeUrl);

		dao.insertWikiSongInfo(songInfo);

		// 곡의 난이도 설명
		String kantanLevel = req.getParameter("kantanLevel");
		songLevel.put("kantanLevel", kantanLevel);
		String hutsuuLevel = req.getParameter("hutsuuLevel");
		songLevel.put("hutsuuLevel", hutsuuLevel);
		String muzukashiLevel = req.getParameter("muzukashiLevel");
		songLevel.put("muzukashiLevel", muzukashiLevel);
		String oniLevel = req.getParameter("oniLevel");
		songLevel.put("oniLevel", oniLevel);
		String uraLevel = req.getParameter("uraLevel");
		songLevel.put("uraLevel", uraLevel);
		
		dao.insertWikiSongLevel(songLevel);

		/* 파일 업로드 ! */
		if (!songImage.getOriginalFilename().isEmpty()) {

			songImage.transferTo(new File(FileSavePath + "/wikiImage", songImage.getOriginalFilename())); //위키 이미지 경로에 파일떨굽니다.

			String fileName = songImage.getOriginalFilename();

			imageParams.put("songImageName", fileName); // SAMPLE_SONG

			imageParams.put("filePath", "/resources/assets/wikiImage"); // 위키 이미지 저장경로
			
			dao.insertImageInfo(imageParams);
		
			PrintWriter out = res.getWriter();

			out.println("<script>alert('success!'); window.location.href = '/gowiki'; </script>");

			out.flush();

		} else {
			PrintWriter out = res.getWriter();
			out.println("<script>alert('success!'); window.location.href = '/gowiki'; </script>");

			out.flush();

		}
		

		return "";
	}
	@RequestMapping("/searchSongWiki")
	public String searchSongWiki(HttpServletRequest req , Model model) {
		HashMap<String, Object> searchParam = new HashMap<String, Object>();
		
		String songName = req.getParameter("songName");
		
		searchParam.put("songName",songName);
		
		List<TaikoWikiDto> getWikiResult = dao.getWikiResult(searchParam);
		
		model.addAttribute("getWikiResult",getWikiResult);
		model.addAttribute("songName",songName);
		
		
		return "searchSongWiki";
	}
	
	
	@RequestMapping(value = "/uploadFreeBoardFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
	  HashMap<String , Object> paramMap = new HashMap<String, Object>();
	  try {
	    String fileName = uploadfile.getOriginalFilename();
	    String filePath = Paths.get(BoardFilePath, fileName).toString();
	   
	    if(fileName.equals("") || fileName == null) {
	    	fileName = "";
	    	filePath = "";
	    	paramMap.put("fileName", fileName);
		    paramMap.put("filePath", filePath);
		    
		    dao.uploadFreeBoardFile(paramMap);
	    }else {
	    	BufferedOutputStream stream =
	    	new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	    	stream.write(uploadfile.getBytes());
	        stream.close();
	        paramMap.put("fileName", fileName);
	        //BoardFilePath ==> resources/assets/freeBoardImage subString 
	        paramMap.put("filePath", "resources/assets/freeBoardImage");
	    	dao.uploadFreeBoardFile(paramMap);
	    	
	    }
	  
	  }
	  catch (Exception e) {
	    System.out.println(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	} 
	@DeleteMapping("/deleteFreeBoard")
	public ResponseEntity<?> deleteFreeBoard(@RequestParam("idx") String idx , @RequestParam("password")String password){
		try {
			String deleteTargetPassword = dao.deleteTargetPassoword(idx);
			
			String salt = password;
			String hex = null;
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(salt.getBytes()); 
			// 암호화 된 비밀번호 
			hex = String.format("%128x", new BigInteger(1, msg.digest()));
			
			if(deleteTargetPassword.equals(hex)) {
				dao.deleteFreeBoard(idx);
				dao.deleteFreeBoardImage(idx);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}catch(Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/freeBoard")
	public ResponseEntity<?> writeFreeBoard(@RequestParam("nickName") String nickName,@RequestParam("comment") String comment,@RequestParam("password") String password) throws NoSuchAlgorithmException {
		HashMap<String,Object> paramMap = new HashMap<String , Object>();
		String salt = password;
		String hex = null;
		MessageDigest msg = MessageDigest.getInstance("SHA-512");
		msg.update(salt.getBytes()); 
		// 암호화 된 비밀번호 
		hex = String.format("%128x", new BigInteger(1, msg.digest()));
	    
		try {
			paramMap.put("comment", comment);
			paramMap.put("nickName", nickName);
			paramMap.put("password", hex);
			
			dao.writeFreeBoard(paramMap);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/getFreeBoardReplyComments")
	public ResponseEntity<?> getFreeBoardReplyComments(HttpServletRequest req){
		String commentIdx = req.getParameter("idx");
		
		try {
			List<FreeBoardReplyCommentDto> replyCommentList = dao.getReplyCommentList(commentIdx);
			System.out.println("commentIdx======>"+commentIdx);
			return new ResponseEntity<>(replyCommentList, HttpStatus.OK);
		}catch(Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@GetMapping("/freeBoard")
	public ResponseEntity<?> getFreeBoard(HttpServletRequest req){
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> resultMap = new HashMap<String , Object>();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		String nowPage = req.getParameter("nowPage");
		String cntPerPage = "10";
		if (nowPage == null) {
			nowPage = "1";

		}
		try {
			int total = dao.freeBoardCount();
			PagingDto pagingResult = new PagingDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			paramMap.put("start", pagingResult.getStart());
			paramMap.put("end", pagingResult.getEnd());
			List<FreeBoard> boardList = dao.getFreeBoard(paramMap);
			resultMap.put("boardList", boardList);
			resultMap.put("nowPage", nowPage);
			resultMap.put("end", pagingResult.getEnd());
			
			return new ResponseEntity<>(resultMap,headers, HttpStatus.OK);
			
		}catch(Exception e) {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/writeReply")
	public ResponseEntity<?> writeReplies(@RequestParam("nickName") String nickName,@RequestParam("password") String password,@RequestParam("contents") String contents,@RequestParam("idx") String idx) throws NoSuchAlgorithmException{
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		String salt = password;
		String hex = null;
		MessageDigest msg = MessageDigest.getInstance("SHA-512");
		msg.update(salt.getBytes()); 
		// 암호화 된 비밀번호 
		hex = String.format("%128x", new BigInteger(1, msg.digest()));
		paramMap.put("nickName",nickName);
		paramMap.put("password",hex);
		paramMap.put("contents",contents);
		paramMap.put("idx",idx);
		
		try {
			
			dao.writeReplies(paramMap);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			 System.out.println(e);
			 //return new ResponseEntity<>(HttpStatus.);
			 return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			
		}
	}

}
