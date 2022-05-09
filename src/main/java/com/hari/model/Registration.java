package com.hari.model;

public class Registration {
	private String name;
	private String userName;
	private String userMobileNumber;
	private int age;
	private String userEmail;
	private String userPassword;
	private int yearOfStudy;
	private String branch;
	private String hOrD;
	private String needBus;
	public String gethOrD() {
		return hOrD;
	}
	public void sethOrD(String hOrD) {
		this.hOrD = hOrD;
	}
	public String getNeedBus() {
		return needBus;
	}
	public void setNeedBus(String needBus) {
		this.needBus = needBus;
	}
	private static String sessionMail;
//	public Registration(String name,String mobile)
//	{
//		this.name=name;
//		this.userMobileNumber=mobile;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public static String getSessionMail() {
		return sessionMail;
	}
	public static void setSessionMail(String sessionMail) {
		Registration.sessionMail = sessionMail;
	}

}
