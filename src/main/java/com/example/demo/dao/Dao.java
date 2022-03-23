package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.example.demo.controller.PagingDto;
import com.example.demo.freeBoardDto.FreeBoard;
import com.example.demo.freeBoardDto.FreeBoardReplyCommentDto;
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
	// 자유게시판 이미지 업로드 
	public void uploadFreeBoardFile(HashMap<String, Object> paramMap) {
		 sqlSession.insert(NAMESPACE+"insertFreeBoardFile",paramMap);
		
	}
	// 자유게시판 댓글 작성 
	public void writeFreeBoard(HashMap<String, Object> paramMap) {
		sqlSession.insert(NAMESPACE+"writeFreeBoard",paramMap);
		
	}

	public List<FreeBoard> getFreeBoard(HashMap<String, Object> paramMap) {
		return sqlSession.selectList(NAMESPACE+"getFreeBoard",paramMap);
		
	}

	public int freeBoardCount() {
		return sqlSession.selectOne(NAMESPACE+"getFreeBoardCount");
	}
	// 삭제할 게시물의 비밀번호를 idx를 이용하여 조회 
	public String deleteTargetPassoword(String idx) {
		return sqlSession.selectOne(NAMESPACE+"deleteTargetPassoword",idx);
	}
	// idx 를 기준으로 게시물 삭제 
	public void deleteFreeBoard(String idx) {
		sqlSession.delete(NAMESPACE+"deleteFreeBoard",idx);
		
	}
	// idx 를 기준으로 이미지 관련 데이터 삭제 
	public void deleteFreeBoardImage(String idx) {
		sqlSession.delete(NAMESPACE+"deleteFreeBoardImage",idx);
		
	}
	// 상위 부모 idx 게시물에 대한 하위 idx child 댓글 가져오기 
	public List<FreeBoardReplyCommentDto> getReplyCommentList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+"getReplyCommentList",paramMap);
	}
	// 댓글작성 
	public void writeReplies(HashMap<String, Object> paramMap) {
		sqlSession.insert(NAMESPACE+"writeReplies",paramMap);
		
	}
	// 하위댓글 count 
	public int getReplyCount(HashMap<String, Object> paramMap) {
		
		return sqlSession.selectOne(NAMESPACE+"getReplyCount",paramMap);
	}
	// 위키 수정 
	public void updateSongWiki(HashMap<String, Object> songInfo) {
		 sqlSession.update(NAMESPACE+"updateSongWiki",songInfo);
		
	}
	// 위키 레벨수정 
	public void editWikiSongLevel(HashMap<String, Object> songLevel) {
		sqlSession.update(NAMESPACE+"editWikiSongLevel",songLevel);
		
	}
	// 위키 이미지 수정 
	public void updateSongImage(HashMap<String, Object> imageParams) {
		sqlSession.update(NAMESPACE+"updateSongImage",imageParams);
		
	}

}
