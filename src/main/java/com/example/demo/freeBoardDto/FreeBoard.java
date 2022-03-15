package com.example.demo.freeBoardDto;

public class FreeBoard {
	private String nickName;
	private String boardComment;
	private String dateTime;
	private int idx;
	private String fileName;
	private String filePath;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "FreeBoard [nickName=" + nickName + ", boardComment=" + boardComment + ", dateTime=" + dateTime
				+ ", idx=" + idx + ", fileName=" + fileName + ", filePath=" + filePath + ", getNickName()="
				+ getNickName() + ", getBoardComment()=" + getBoardComment() + ", getDateTime()=" + getDateTime()
				+ ", getIdx()=" + getIdx() + ", getFileName()=" + getFileName() + ", getFilePath()=" + getFilePath()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
}
