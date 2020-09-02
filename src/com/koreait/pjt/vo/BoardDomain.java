package com.koreait.pjt.vo;

public class BoardDomain extends BoardVO{
	private String user_nm;
	private int like;
	private int like_count;
	private int recode_cnt;
	private String searchText;
	private int maxRecord;
	private int minRecord;
	private String user_profile_img;
	private int my_like;
	private int board_like_cnt;
	private int board_cmt_cnt;
	private String searchType;

	public String getUser_profile_img() {
		return user_profile_img;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	
	public int getMy_like() {
		return my_like;
	}
	public void setMy_like(int my_like) {
		this.my_like = my_like;
	}
	public int getBoard_like_cnt() {
		return board_like_cnt;
	}
	public void setBoard_like_cnt(int board_like_cnt) {
		this.board_like_cnt = board_like_cnt;
	}
	public int getBoard_cmt_cnt() {
		return board_cmt_cnt;
	}
	public void setBoard_cmt_cnt(int board_cmt_cnt) {
		this.board_cmt_cnt = board_cmt_cnt;
	}
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
