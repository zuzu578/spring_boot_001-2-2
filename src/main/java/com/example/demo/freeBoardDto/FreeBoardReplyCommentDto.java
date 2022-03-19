package com.example.demo.freeBoardDto;

public class FreeBoardReplyCommentDto {
	private int idx;
	private int boardIdx;
	private String replyCommentNickName;
	private String replyComment;
	private String password;
	private String dateTime;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getReplyCommentNickName() {
		return replyCommentNickName;
	}
	public void setReplyCommentNickName(String replyCommentNickName) {
		this.replyCommentNickName = replyCommentNickName;
	}
	public String getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "FreeBoardReplyCommentDto [idx=" + idx + ", boardIdx=" + boardIdx + ", replyCommentNickName="
				+ replyCommentNickName + ", replyComment=" + replyComment + ", password=" + password + ", dateTime="
				+ dateTime + ", getIdx()=" + getIdx() + ", getBoardIdx()=" + getBoardIdx()
				+ ", getReplyCommentNickName()=" + getReplyCommentNickName() + ", getReplyComment()="
				+ getReplyComment() + ", getPassword()=" + getPassword() + ", getDateTime()=" + getDateTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	

}
