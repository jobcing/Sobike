package org.jpg.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jpg.domain.BikeVO;
import org.jpg.persistence.BikeDAO;
import org.jpg.persistence.MemberDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BikeServiceImpl implements BikeService {

	@Inject
	private BikeDAO bikeDao;
	
	@Inject
	private MemberDAO memberDao;
	
	@Override
	public List<BikeVO> showList() throws Exception {
		return bikeDao.select();
	}

	@Override
	public BikeVO selectByNum(int bikenum) throws Exception {
		return bikeDao.selectByNum(bikenum);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void rent(int bikenum, Date startDate, String classnum) throws Exception {
		memberDao.rent(classnum, bikenum);
		bikeDao.rent(bikenum, startDate);
	}

	@Override
	public void turnin(int bikenum, int loc) throws Exception {
		memberDao.turnin(bikenum);
		bikeDao.turnin(bikenum, loc);
	}

	@Override
	public int selectByClassnum(String classnum) throws Exception {
		return memberDao.selectByClassnum(classnum);
	}
}
