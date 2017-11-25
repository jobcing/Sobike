package org.jpg.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.jpg.domain.BikeVO;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDAOImpl implements BikeDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.jpg.mapper.BikeMapper";
	
	@Override
	public List<BikeVO> select() throws Exception {
		return session.selectList(namespace + ".list");
	}
	
	@Override
	public BikeVO selectByNum(int bikenum) throws Exception {
		return session.selectOne(namespace + ".selectByNum", bikenum);
	}

	@Override
	public void rent(int bikenum, Date startDate) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bikenum", bikenum);
		paramMap.put("startDate", startDate);
		
		session.update(namespace + ".rent", paramMap);
	}

	@Override
	public void turnin(int bikenum, int loc) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bikenum", bikenum);
		paramMap.put("loc", loc);
		
		session.update(namespace + ".turnin", paramMap);
	}
}
