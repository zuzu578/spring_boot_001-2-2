package com.example.demo.freeBoardDto;

public class FreeBoard {
	private String nickName;
	private String boardComment;
	private String dateTime;
	private int idx;
	private String fileName;
	private String filePath;
	private String commentNickName;
	private String replyComment;
	private String replyCommentDateTime;
	private String boardIdx;
	private String commentIdx;
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
	public String getCommentNickName() {
		return commentNickName;
	}
	public void setCommentNickName(String commentNickName) {
		this.commentNickName = commentNickName;
	}
	public String getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}
	public String getReplyCommentDateTime() {
		return replyCommentDateTime;
	}
	public void setReplyCommentDateTime(String replyCommentDateTime) {
		this.replyCommentDateTime = replyCommentDateTime;
	}
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(String commentIdx) {
		this.commentIdx = commentIdx;
	}
	@Override
	public String toString() {
		return "FreeBoard [nickName=" + nickName + ", boardComment=" + boardComment + ", dateTime=" + dateTime
				+ ", idx=" + idx + ", fileName=" + fileName + ", filePath=" + filePath + ", commentNickName="
				+ commentNickName + ", replyComment=" + replyComment + ", replyCommentDateTime=" + replyCommentDateTime
				+ ", boardIdx=" + boardIdx + ", commentIdx=" + commentIdx + ", getNickName()=" + getNickName()
				+ ", getBoardComment()=" + getBoardComment() + ", getDateTime()=" + getDateTime() + ", getIdx()="
				+ getIdx() + ", getFileName()=" + getFileName() + ", getFilePath()=" + getFilePath()
				+ ", getCommentNickName()=" + getCommentNickName() + ", getReplyComment()=" + getReplyComment()
				+ ", getReplyCommentDateTime()=" + getReplyCommentDateTime() + ", getBoardIdx()=" + getBoardIdx()
				+ ", getCommentIdx()=" + getCommentIdx() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
