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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private static String FILE_SERVER_PATH = "/Users/helloworld/git/spring_boot_001-2-5/src/main/webapp";
	//private static String FileSavePath = "/Users/helloworld/git/springBoot-Transaction/spring_boot_001 2/src/main/webapp/resources/assets";
	private static String FileSavePath = "/Users/helloworld/git/spring_boot_001-2-5/src/main/webapp/resources/assets";
	//private static String fileDownLoad = "/Users/helloworld/git/spring_boot_001-2-5/src/main/webapp/resources/assets";
	@Autowired
	private Dao dao;

	// jsp MVC pattern
	// ??????????????? ????????? ?????? ??????
	@RequestMapping("/")
	public String testJsp(HttpServletRequest req, Model model) {

		return "test";
	}
	
	@RequestMapping("/freeTalkBoard")
	public String freeTalkBoard(HttpServletRequest req , Model model) {
		
		return "freeTalkBoard";
	}

	/**
	 * ????????? ????????? ????????? ?????????
	 */
	@RequestMapping("/customFileUpload")
	public String customFileUpload() {

		return "customFileUpload";
	}

	/**
	 * file upload page ??? ??????!
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
	 * ????????? , ?????? ?????? ????????? db ??? ???????????????
	 * 
	 */
	@RequestMapping(value = "/customeUpload", method = RequestMethod.POST)
	public String customeUpload(@RequestParam("songData") MultipartFile file,
			@RequestParam("musicSheet") MultipartFile file2, ModelAndView mv, HttpServletRequest req, Model model,
			HttpServletResponse res) throws IllegalStateException, IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter ??? ?????? ??????!

		String select_song_status = req.getParameter("select_song_status");
		String songGenre = req.getParameter("songGenre");
		String songName = req.getParameter("songName");

		paramMap.put("select_song_status", select_song_status);
		paramMap.put("songGenre", songGenre);
		paramMap.put("songName", songName);

		if (!file.getOriginalFilename().isEmpty() && !file2.getOriginalFilename().isEmpty()) {

			file.transferTo(new File(FileSavePath, file.getOriginalFilename())); // => ???????????? ????????? ??????
			file2.transferTo(new File(FileSavePath, file2.getOriginalFilename())); // => ??????zip???????????? ????????? ??????

			String fileName = file.getOriginalFilename(); // song(ogg File)
			String musicSheetFileName = file2.getOriginalFilename(); // musicSheet( zip file ..ect )

			paramMap.put("sampleSongName", fileName); // SAMPLE_SONG
			paramMap.put("musicSheetFileName", musicSheetFileName); // FILE_NAME
			paramMap.put("filePath", "/resources/assets/");

			dao.insertCustomeSongs(paramMap); // 2) TB_TAIKO_SONGS => ??? ?????? , ?????? , ?????? ,??????(?????? ,????????? ?????? )

			dao.insertCustomeSongInfo(paramMap); // 3) TB_TAIKO_FILES => ??? ?????? , ??????

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
	 * ?????? ???????????? ????????? , db??? ???????????????. (?????? ?????????)
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 * 
	 */
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUpload(@RequestParam("songData") MultipartFile file,
			@RequestParam("musicSheet") MultipartFile file2, ModelAndView mv, HttpServletRequest req, Model model,
			HttpServletResponse res) throws IllegalStateException, IOException {

		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter ??? ?????? ??????!

		// String uploadSelect = req.getParameter("uploadSelect");
		String select_song_status = req.getParameter("select_song_status");
		String songName = req.getParameter("songName");
		String songNo = req.getParameter("songNo");

		if (songNo == null) {
			songNo = "";
		}

		paramMap.put("select_song_status", select_song_status); // ?????? , ????????? ??????
		paramMap.put("songName", songName); // ?????? ??????
		paramMap.put("songNo", songNo); // ?????? ???????????? ??????

		/**
		 * 1) tb_TB_TAIKO_SONGS => ?????? , ?????? ??????????????? , ???????????? 2) tb_tb_taiko_file => ???????????? , ??????
		 * ??????
		 * 
		 */
		if (!file.getOriginalFilename().isEmpty() && !file2.getOriginalFilename().isEmpty()) {

			file.transferTo(new File(FileSavePath, file.getOriginalFilename())); // songData(?????? ????????? ??????????????? ??????)
			file2.transferTo(new File(FileSavePath, file2.getOriginalFilename())); // musicSheet(?????? ????????? ??????????????? ??????)

			String taikoSongName = file.getOriginalFilename(); // ?????? ????????? ???????????? ?????????.
			String musicSheetFileName = file2.getOriginalFilename(); // ?????? ????????? ???????????? ?????????.
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
	 * ?????? ???????????????.
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/searchSongs", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray searchSongs(HttpServletRequest req) {

		String searchSongType = req.getParameter("searchSongType");
		String searchKeyword = req.getParameter("searchKeyword");

		HashMap<String, Object> paramMap = new HashMap<String, Object>(); // parameter ??? ?????? ??????!

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

	// ???????????? ?????????,
	// db ??? ????????? ??????????????? ?????? / ???????????? ????????? ?????? , ?????? , ????????? ?????????????????? ???????????? ???????????????.
	@RequestMapping(value = "/getDataBaseSong", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getDataBaseSong(HttpServletRequest req) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		String nowPage = req.getParameter("nowPage");
		String cntPerPage = "20";

		String genre = req.getParameter("genre");
		String searchSelect = req.getParameter("searchSelect");

		paramMap.put("genre", genre);
		paramMap.put("searchSelect", searchSelect); // ?????? , ????????? ??????

		if (nowPage == null) {
			nowPage = "1";

		}
		// ?????? ????????? ?????? ????????? ????????????.
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
	 * ??????????????? ?????? ????????? ??????????????????
	 */
	@RequestMapping(value = "/doCrawling", method = RequestMethod.GET)
	public String doCrawling() {

		return "doCrawling";
	}

	@RequestMapping("/deleteSongData")
	public String deleteSongData(HttpServletRequest req, HttpServletResponse res) {
		// ????????? ????????? ????????? ???????????????.
		String deleteGenre = req.getParameter("deleteGenre");
		// ????????????????????? ?????? ????????? ???????????? ?????? ???????????????.
		dao.deleteOldSongList();
		return "?????????????????????.";
	}

	@RequestMapping("/startCrawling")
	public void startCrawling(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// ???????????? ?????? ????????? ???????????????.
		String crawlingGenre = req.getParameter("crawlingGenre");

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crawlingGenre", crawlingGenre);

		Document doc = Jsoup.connect("https://taiko.namco-ch.net/taiko/songlist/" + crawlingGenre + ".php#sgnavi")
				.get();
		Elements rowList = doc.select("table"); // ???????????? ???????????????.
		Elements songGenre = doc.select("h3"); // ????????? ???????????? ????????? ???????????????.

		for (Element row : rowList) {
			Elements cellList = row.select("tr"); // (th)
			// ????????????????????? ????????? ?????? ???????????????.
			for (int i = 2; i < cellList.size(); i++) {

				paramMap.put("songGenre", songGenre.text());
				paramMap.put("songList", cellList.get(i).text());

				dao.insertSongs(paramMap);
				// songs.put(i, cellList.get(i).text());
			}
		}

		PrintWriter out = res.getWriter();

		out.println("<script>alert('??????????????? ?????? ?????????????????????!'); window.location.href = '/'; </script>");

		out.flush();

	}

	/**
	 * 
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 * 
	 *                     ??????????????? ?????? ???????????? ??????????????????.
	 */
	@RequestMapping(value = "/getSongList", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, Object> taikoCrawling(HttpServletRequest req, Locale locale, Model model) throws IOException {

		/**
		 * ?????????? 1) namco => ?????????????????? 2) pops => jpop 3) kids => kids 4) anime => anime
		 * 5) vocaloid => ??????????????? 6) game => ???????????? 7) variety => ??????????????? 8) classic => ?????????
		 * 
		 */
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String genre = "namco";

		JSONObject songs = null;
		JSONParser parser = new JSONParser();
		songs = new JSONObject();

		Document doc = Jsoup.connect("https://taiko.namco-ch.net/taiko/songlist/" + genre + ".php#sgnavi").get();
		Elements rowList = doc.select("table"); // ???????????? ???????????????.
		Elements songGenre = doc.select("h3"); // ????????? ???????????? ????????? ???????????????.

		// System.out.println("genre => " + songGenre.text());

		for (Element row : rowList) {
			Elements cellList = row.select("tr"); // (th)
			for (int i = 2; i < cellList.size(); i++) {
				// db ??? ???????????? ???????????????.
				paramMap.put("songGenre", songGenre.text());
				paramMap.put("songList", cellList.get(i).text());

				dao.insertSongs(paramMap);
				songs.put(i, cellList.get(i).text());
			}
		}
		return songs;
	}

	/*
	 * desc : map list data??? jsonObject ??? ???????????????. ???????????? jsonArray ??? ???????????????.
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

	// ????????? ?????? (ogg file) ????????????!
	// ex) // => /Users/helloworld/git/springBoot-Transaction/spring_boot_001
	// 2/src/main/webapp/resources/assets/?????????????????? ???????????????.ogg
	@RequestMapping("/songDownLoad")
	public void songDownLoad(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelAndView mav) {
		String fileName = req.getParameter("fileName");
		String filePath = req.getParameter("filePath");
		TaikoSongDownloadFile fileDown = new TaikoSongDownloadFile(); // ?????????????????? ????????????
		try {
			//fileDown.filDown(req, res, FILE_SERVER_PATH + filePath, fileName, fileName);
			fileDown.filDown(req, res, FILE_SERVER_PATH + filePath, fileName, fileName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// ??????????????? ?????? ?????? ?????????????????? ???????????????.
	@RequestMapping("/makedoc")
	public String makedoc() {

		return "makedoc";
	}

	// ??????????????? ?????? ???????????? ???????????????.
	@RequestMapping("/gowiki")
	public String gowiki(Model model) {
		int count = dao.getWikiCount();
		model.addAttribute("count",count);
		return "gowiki";
	}
	// ??????????????? ?????? ????????? ???????????????.

	@RequestMapping(value = "/doMakingDoc", method = RequestMethod.POST)
	public String doMakingDoc(@RequestParam("songImage") MultipartFile songImage, ModelAndView mv,
			HttpServletRequest req, Model model, HttpServletResponse res) throws IllegalStateException, IOException {
		HashMap<String, Object> songLevel = new HashMap<String, Object>(); // tb_taiko_song_level ??? ?????? params
		HashMap<String, Object> songInfo = new HashMap<String, Object>(); // tb_taiko_wiki_content??? ?????? params
		HashMap<String, Object> imageParams = new HashMap<String, Object>(); // TB_TAIKO_IMAGE??? ?????? params

		// ??? ??????
		String songName = req.getParameter("songName");
		String songDescription = req.getParameter("songDescription");
		String song_description2 = req.getParameter("song_description2");
		String songWriter = req.getParameter("songWriter");
		String musicSheetWriter = req.getParameter("musicSheetWriter");
		String musicVersion = req.getParameter("musicVersion");
		String songGenre = req.getParameter("songGenre");
		String youtubeUrl = req.getParameter("youtubeUrl");
		String ip = req.getParameter("ip");
		
		songInfo.put("songName", songName);
		songInfo.put("songDescription", songDescription);
		songInfo.put("song_description2", song_description2);
		songInfo.put("songWriter", songWriter);
		songInfo.put("musicSheetWriter", musicSheetWriter);
		songInfo.put("musicVersion", musicVersion);
		songInfo.put("songGenre", songGenre);
		songInfo.put("youtubeUrl", youtubeUrl);
		songInfo.put("ip", ip);
		
		dao.insertWikiSongInfo(songInfo);

		// ?????? ????????? ??????
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

		/* ?????? ????????? ! */
		if (!songImage.getOriginalFilename().isEmpty()) {

			songImage.transferTo(new File(FileSavePath + "/wikiImage", songImage.getOriginalFilename())); //?????? ????????? ????????? ??????????????????.

			String fileName = songImage.getOriginalFilename();

		    if(fileName.equals("") || fileName == null) {
		    	fileName = "";
		    }

			imageParams.put("songImageName", fileName); // SAMPLE_SONG

			imageParams.put("filePath", "/resources/assets/wikiImage"); // ?????? ????????? ????????????
			
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
	
//	@RequestMapping("/wikiResultLists")
//	public String wikiResultLists(@RequestParam("getWikiResult")List<Object> getWikiResult,Model model) {
//		model.addAttribute("getWikiResult",getWikiResult);
//		return "wikiResultLists";
//	}
	@RequestMapping("/searchSongWiki")
	public String searchSongWiki(HttpServletRequest req , Model model , HttpServletResponse res ) throws IOException {
		HashMap<String, Object> searchParam = new HashMap<String, Object>();
		
		String songName = req.getParameter("songName");
		
		searchParam.put("songName",songName);
		
		List<TaikoWikiDto> getWikiResult = dao.getWikiResult(searchParam);
		if(getWikiResult.size() == 0 ) {
			PrintWriter out = res.getWriter();
			out.println("<script>alert('no data !'); window.location.href = '/gowiki'; </script>");

			out.flush();
		}
		if(getWikiResult.size() >=2) {
			model.addAttribute("songName",songName);
			model.addAttribute("getWikiResult",getWikiResult);
			model.addAttribute("count",getWikiResult.size());
			return "wikiResultLists";
		}
		model.addAttribute("getWikiResult",getWikiResult);
		
		
		return "searchSongWiki";
	}
	
	@RequestMapping("/editSongWiki")
	public String editSongWiki(HttpServletRequest req , Model model) {
		HashMap<String, Object> searchParam = new HashMap<String, Object>();
		String idx = req.getParameter("idx");
		searchParam.put("idx", idx);
		List<TaikoWikiDto> getWikiResult = dao.getWikiResult(searchParam);
		model.addAttribute("getWikiResult",getWikiResult);
		return "editSongWiki";
	}
	@RequestMapping(value ="/editWiki" , method = RequestMethod.POST)
	public String editWiki
	(@RequestParam("songImage") MultipartFile songImage, ModelAndView mv,
			HttpServletRequest req, Model model, HttpServletResponse res) throws IllegalStateException, IOException {
		HashMap<String, Object> songLevel = new HashMap<String, Object>(); // tb_taiko_song_level ??? ?????? params
		HashMap<String, Object> songInfo = new HashMap<String, Object>(); // tb_taiko_wiki_content??? ?????? params
		HashMap<String, Object> imageParams = new HashMap<String, Object>(); // TB_TAIKO_IMAGE??? ?????? params

		// ??? ??????
		String songName = req.getParameter("songName");
		String songDescription = req.getParameter("songDescription");
		String song_description2 = req.getParameter("song_description2");
		String songWriter = req.getParameter("songWriter");
		String musicSheetWriter = req.getParameter("musicSheetWriter");
		String musicVersion = req.getParameter("musicVersion");
		String songGenre = req.getParameter("songGenre");
		String youtubeUrl = req.getParameter("youtubeUrl");
		String idx = req.getParameter("idx");
		String ip = req.getParameter("ip");
		songInfo.put("songName", songName);
		songInfo.put("songDescription", songDescription);
		songInfo.put("song_description2", song_description2);
		songInfo.put("songWriter", songWriter);
		songInfo.put("musicSheetWriter", musicSheetWriter);
		songInfo.put("musicVersion", musicVersion);
		songInfo.put("songGenre", songGenre);
		songInfo.put("youtubeUrl", youtubeUrl);
		songInfo.put("ip", ip);
		songInfo.put("idx", idx);
		dao.updateSongWiki(songInfo);

		// ?????? ????????? ??????
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
		songLevel.put("idx", idx);
		
		dao.editWikiSongLevel(songLevel);

		/* ?????? ????????? ! */
		if (!songImage.getOriginalFilename().isEmpty()) {

			songImage.transferTo(new File(FileSavePath + "/wikiImage", songImage.getOriginalFilename())); //?????? ????????? ????????? ??????????????????.

			String fileName = songImage.getOriginalFilename();

			imageParams.put("songImageName", fileName); // SAMPLE_SONG

			imageParams.put("filePath", "/resources/assets/wikiImage"); // ?????? ????????? ????????????
			imageParams.put("imageStatus","wiki");
			imageParams.put("idx", idx);
			dao.updateSongImage(imageParams);
		
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
			// ????????? ??? ???????????? 
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
	public ResponseEntity<?> writeFreeBoard(@RequestParam("nickName") String nickName,@RequestParam("comment") String comment,@RequestParam("password") String password,@RequestParam("ip") String ip) throws NoSuchAlgorithmException {
		HashMap<String,Object> paramMap = new HashMap<String , Object>();
		String salt = password;
		String hex = null;
		MessageDigest msg = MessageDigest.getInstance("SHA-512");
		msg.update(salt.getBytes()); 
		// ????????? ??? ???????????? 
		hex = String.format("%128x", new BigInteger(1, msg.digest()));
	    
		try {
			paramMap.put("comment", comment);
			paramMap.put("nickName", nickName);
			paramMap.put("password", hex);
			paramMap.put("ip",ip);
			
			dao.writeFreeBoard(paramMap);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/getReplyComments")
	public ResponseEntity<?> getFreeBoardReplyComments(HttpServletRequest req){
		HashMap<String, Object> paramMap = new HashMap<String , Object>();
		String commentIdx = req.getParameter("idx");
		String type = req.getParameter("type");
		paramMap.put("commentIdx", commentIdx);
		paramMap.put("type", type);
		String nowPage = req.getParameter("nowPage");
		String cntPerPage = "10";
		if (nowPage == null) {
			nowPage = "1";

		}
		try {
			int total = dao.getReplyCount(paramMap);
			PagingDto pagingResult = new PagingDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			paramMap.put("start", pagingResult.getStart());
			paramMap.put("end", pagingResult.getEnd());
			List<FreeBoardReplyCommentDto> replyCommentList = dao.getReplyCommentList(paramMap);
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
	public ResponseEntity<?> writeReplies(@RequestParam("nickName") String nickName,@RequestParam("password") String password,@RequestParam("contents") String contents,@RequestParam("idx") String idx,@RequestParam("type") String type,@RequestParam("ip")String ip) throws NoSuchAlgorithmException{
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		String salt = password;
		String hex = null;
		MessageDigest msg = MessageDigest.getInstance("SHA-512");
		msg.update(salt.getBytes()); 
		// ????????? ??? ???????????? 
		hex = String.format("%128x", new BigInteger(1, msg.digest()));
		paramMap.put("nickName",nickName);
		paramMap.put("password",hex);
		paramMap.put("contents",contents);
		paramMap.put("idx",idx);
		paramMap.put("type",type);
		paramMap.put("ip", ip);
		
		try {
			
			dao.writeReplies(paramMap);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			 System.out.println(e);
			 //return new ResponseEntity<>(HttpStatus.);
			 return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			
		}
	}
	@GetMapping("/getRecentWiki")
	public ResponseEntity<?> getRecentWiki(){
			List<TaikoWikiDto> result = dao.getRecentWiki();
			System.out.println("result => "+ result );
			return new ResponseEntity<>(result,HttpStatus.OK);
		
	}
	@PostMapping("/autocomplete")
	public ResponseEntity<?>autocomplete(HttpServletRequest req){
		try {
			String term = req.getParameter("term");
			List<Object> resultList = dao.autoComplete(term);
			
			return new ResponseEntity<>(resultList,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
	}
	

}
