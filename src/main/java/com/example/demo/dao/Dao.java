package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.example.demo.controller.PagingDto;
import com.example.demo.wikidto.TaikoWikiDto;

@Repository
public class Dao {
	protected static final String NAMESPACE = "com.example.demo.dao.";

	@Autowired
	private SqlSession sqlSession;

	// 크롤링한 수록곡을 데이터베이스에 저장합니다.
	public void insertSongs(HashMap<String, Object> paramMap) {

		sqlSession.insert(NAMESPACE + "insertSongs", paramMap);

	}

	// 크롤링한 곡과 데이터베이스에 있는 곡이 일치한지 검색 합니다.
	public List<Map<String, Object>> searchSongs(HashMap<String, Object> paramMap) {

		return sqlSession.selectList(NAMESPACE + "searchSongs", paramMap);
	}

	// 크롤링해서 저장한 수록곡을 데이터베이스에서 가져옵니다.
	public List<Map<String, Object>> getDataBaseSong(HashMap<String, Object> paramMap) {

		return sqlSession.selectList(NAMESPACE + "getDataBaseSong", paramMap);
	}

	/*
	 * 태고의달인 곡 정보 ( 곡이름 , 비공식 / 공식여부 , 장르를 넣습니다.) public void
	 * insertSongInfo(HashMap<String, Object> paramMap) {
	 * 
	 * 
	 * }
	 */
	// 태고의달인 곡 (노래 이름 , 경로를 넣습니다.)
	public void insertSongContents(HashMap<String, Object> paramMap) {

		sqlSession.insert(NAMESPACE + "insertSongContents", paramMap);

	}

	// 음원이 없는경우 해당 노래에 음원을 업로드 하기위해 select
	public String selectSongInfo(String songNo) {

		return sqlSession.selectOne(NAMESPACE + "selectSongInfo", songNo);
	}

	// 태고의달인 비공식 곡을 넣습니다.
	public void insertCustomeSongs(HashMap<String, Object> paramMap) {
		sqlSession.insert(NAMESPACE + "insertCustomeSongs", paramMap);

	}

	// 태고의달인 비공식 곡정보 (파일 경로 , 파일이름 을 넣습니다)
	public void insertCustomeSongInfo(HashMap<String, Object> paramMap) {
		sqlSession.insert(NAMESPACE + "insertCustomeSongInfo", paramMap);

	}

	/*
	 * // 만약 새롭게 ( 공식 , 비공식 ) 저장한 곡이라면 , 저장한 곡의 번호를 가져와줍니다. public String
	 * getSongNumber(HashMap<String, Object> paramMap) { // TODO Auto-generated
	 * method stub return sqlSession.selectOne(NAMESPACE + "getSongNumber",
	 * paramMap); }
	 */
	// 기존에 크롤링하여 넣어두었던 옛날 곡들을 제거합니다.
	public void deleteOldSongList() {
		sqlSession.delete(NAMESPACE + "deleteOldSongList");
	}

	// 해당장르의 곡 갯수를 구합니다.
	public int songAllCount(HashMap<String, Object> paramMap) {

		return sqlSession.selectOne(NAMESPACE + "songAllCount", paramMap);
	}

	public List<Map<String, Object>> getPagination(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + "getPagination", paramMap);
	}

	// 태고위키 tb_taiko_wiki_content 테이블에 넣을 데이터를 넣습니다.
	public void insertWikiSongInfo(HashMap<String, Object> songInfo) {

		sqlSession.insert(NAMESPACE + "insertWikiSongInfo", songInfo);

	}

	// 태고위키 tb_taiko_song_level 에 넣을 데이터를 넣습니다. (곡 설명 에대한 난이도 )
	public void insertWikiSongLevel(HashMap<String, Object> songInfo) {

		sqlSession.insert(NAMESPACE + "insertWikiSongLevel", songInfo);

	}

	// 태고위키 TB_TAIKO_IMAGE 에 넣을 데이터를 넣습니다 (태고 위키 곡 설명이미지에 대한 데이터를 넣는 테이블 )
	public void insertImageInfo(HashMap<String, Object> imageParams) {

		sqlSession.insert(NAMESPACE + "insertImageInfo", imageParams);

	}

	public List<TaikoWikiDto> getWikiResult(HashMap<String, Object> searchParam) {
		return sqlSession.selectList(NAMESPACE + "getWikiResult", searchParam);
	}

}
