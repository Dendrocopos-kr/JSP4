package com.koreait.pjt.vo;

public class BoardCmtVO{
	private int i_board;
	private int i_cmt;
	private int i_user;
	private int i_recmt;
	private int is_del;
	private String cmt;
	private String r_dt;
	private String m_dt;
	private String user_nm;
	private String user_profile_img;
	
	public String getUser_nm() {
		return user_nm;
	}
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getM_dt() {
		return m_dt;
	}
	public void setM_dt(String m_dt) {
		this.m_dt = m_dt;
	}
	public int getI_cmt() {
		return i_cmt;
	}
	public void setI_cmt(int i_cmt) {
		this.i_cmt = i_cmt;
	}
	public int getI_recmt() {
		return i_recmt;
	}
	public void setI_recmt(int i_recmt) {
		this.i_recmt = i_recmt;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
}
