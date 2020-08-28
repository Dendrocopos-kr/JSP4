package com.koreait.pjt.vo;

public class BoardDomain extends BoardVO{
	private String user_nm;
	private int like;
	private int like_count;
	private int recode_cnt;
	private String searchText;
	private int maxRecord;
	private int minRecord;
	
	public int getMaxRecord() {
		return maxRecord;
	}
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}
	public int getMinRecord() {
		return minRecord;
	}
	public void setMinRecord(int minRecord) {
		this.minRecord = minRecord;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getRecode_cnt() {
		return recode_cnt;
	}
	public void setRecode_cnt(int recode_cnt) {
		this.recode_cnt = recode_cnt;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

}
