package org.jpg.domain;

import java.util.Date;

public class MemberVO {
	
	private String classnum;
	private int bikenum;
	private String username;
	private String regnum;
	private Date usagedate;
	private boolean status;
	
	public void setClassnum(String classnum){
		this.classnum = classnum;
	}
	
	public String getClassnum(){
		return this.classnum;
	}
	
	public void setBikenum(int bikenum){
		this.bikenum = bikenum;
	}
	
	public int getBikenum(){
		return this.bikenum;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setRegnum(String regnum){
		this.regnum = regnum;
	}
	
	public String getRegnum(){
		return this.regnum;
	}
	
	public void setUsagedate(Date usagedate){
		this.usagedate = usagedate;
	}
	
	public Date getUsagedate(){
		return this.usagedate;
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}
	
	public boolean getStatus(){
		return this.status;
	}
}
