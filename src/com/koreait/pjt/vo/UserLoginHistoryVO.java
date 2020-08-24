package com.koreait.pjt.vo;

public class UserLoginHistoryVO {
	private int i_history;
	private int i_user;
	private String ip_addr;
	private String os;
	private String brower;
	
	public int getI_history() {
		return i_history;
	}
	public void setI_history(int i_history) {
		this.i_history = i_history;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrower() {
		return brower;
	}
	public void setBrower(String brower) {
		this.brower = brower;
	}
}
