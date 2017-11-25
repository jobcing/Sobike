package org.jpg.service;

import java.util.Date;
import java.util.List;

import org.jpg.domain.BikeVO;

public interface BikeService {
	public List<BikeVO> showList() throws Exception;
	
	public BikeVO selectByNum(int bikenum) throws Exception;
	
	public int selectByClassnum(String classnum) throws Exception;
	
	public void rent(int bikenum, Date startDate, String classnum) throws Exception;
	
	public void turnin(int bikenum, int loc) throws Exception;
}
