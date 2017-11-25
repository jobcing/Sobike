package org.jpg.domain;

import java.util.Date;

public class BikeVO {
	
	private int bikenum;
	private boolean status;
	private Date usagedate;
	private int location;
	
	public void setBikenum(int bikenum){
		this.bikenum = bikenum;
	}
	
	public int getBikenum(){
		return this.bikenum;
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}
	
	public void setLocation(int location){
		this.location = location;
	}
	
	public boolean getStatus(){
		return this.status;
	}
	
	public void setUsagedate(Date usagedate){
		this.usagedate = usagedate;
	}
	
	public Date getUsagedate(){
		return this.usagedate;
	}
	
	public int getLocation(){
		return this.location;
	}
}
