package com.example.demo.wikidto;

public class TaikoWikiDto {
	private String songDescription2;
	private String songWriter;
	private String songRecordVersion;
	private String songName;
	private String songGenre;
	private String songNo;
	private String songDescription;
	private String kantan;
	private String hutsuu;
	private String muzukashi;
	private String oni;
	private String ura;
	private String fileName;
	private String filePath;
	private String youtubeUrl;

	public String getSongDescription2() {
		return songDescription2;
	}

	public void setSongDescription2(String songDescription2) {
		this.songDescription2 = songDescription2;
	}

	public String getSongWriter() {
		return songWriter;
	}

	public void setSongWriter(String songWriter) {
		this.songWriter = songWriter;
	}

	public String getSongRecordVersion() {
		return songRecordVersion;
	}

	public void setSongRecordVersion(String songRecordVersion) {
		this.songRecordVersion = songRecordVersion;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongGenre() {
		return songGenre;
	}

	public void setSongGenre(String songGenre) {
		this.songGenre = songGenre;
	}

	public String getSongNo() {
		return songNo;
	}

	public void setSongNo(String songNo) {
		this.songNo = songNo;
	}

	public String getSongDescription() {
		return songDescription;
	}

	public void setSongDescription(String songDescription) {
		this.songDescription = songDescription;
	}

	public String getKantan() {
		return kantan;
	}

	public void setKantan(String kantan) {
		this.kantan = kantan;
	}

	public String getHutsuu() {
		return hutsuu;
	}

	public void setHutsuu(String hutsuu) {
		this.hutsuu = hutsuu;
	}

	public String getMuzukashi() {
		return muzukashi;
	}

	public void setMuzukashi(String muzukashi) {
		this.muzukashi = muzukashi;
	}

	public String getOni() {
		return oni;
	}

	public void setOni(String oni) {
		this.oni = oni;
	}

	public String getUra() {
		return ura;
	}

	public void setUra(String ura) {
		this.ura = ura;
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

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	@Override
	public String toString() {
		return "TaikoWikiDto [songDescription2=" + songDescription2 + ", songWriter=" + songWriter
				+ ", songRecordVersion=" + songRecordVersion + ", songName=" + songName + ", songGenre=" + songGenre
				+ ", songNo=" + songNo + ", songDescription=" + songDescription + ", kantan=" + kantan + ", hutsuu="
				+ hutsuu + ", muzukashi=" + muzukashi + ", oni=" + oni + ", ura=" + ura + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", youtubeUrl=" + youtubeUrl + "]";
	}

}
