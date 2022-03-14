package com.example.demo.freeBoardDto;

public class FreeBoardParam {
	private int idx;
	private String fileName;
	private String filePath;
	private String nickname;
	private String boardComment;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	@Override
	public String toString() {
		return "FreeBoardParam [idx=" + idx + ", fileName=" + fileName + ", filePath=" + filePath + ", nickname="
				+ nickname + ", boardComment=" + boardComment + ", getIdx()=" + getIdx() + ", getFileName()="
				+ getFileName() + ", getFilePath()=" + getFilePath() + ", getNickname()=" + getNickname()
				+ ", getBoardComment()=" + getBoardComment() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
