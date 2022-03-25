package com.example.demo.excelDownloadDto;

public class ExcelObjects {
	private int songNo;
	private String songName;
	private String genre;
	private String songType;
	public int getSongNo() {
		return songNo;
	}
	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSongType() {
		return songType;
	}
	public void setSongType(String songType) {
		this.songType = songType;
	}
	@Override
	public String toString() {
		return "ExcelObjects [songNo=" + songNo + ", songName=" + songName + ", genre=" + genre + ", songType="
				+ songType + ", getSongNo()=" + getSongNo() + ", getSongName()=" + getSongName() + ", getGenre()="
				+ getGenre() + ", getSongType()=" + getSongType() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
