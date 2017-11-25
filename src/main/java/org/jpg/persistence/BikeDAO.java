package org.jpg.persistence;

import java.util.Date;
import java.util.List;

import org.jpg.domain.BikeVO;

public interface BikeDAO {
	
	public List<BikeVO> select() throws Exception;
	
	public BikeVO selectByNum(int bikenum) throws Exception;
	
	public void rent(int bikenum, Date startDate) throws Exception;
	
	public void turnin(int bikenum, int loc) throws Exception;
}
