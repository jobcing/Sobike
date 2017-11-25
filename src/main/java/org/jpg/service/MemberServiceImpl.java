package org.jpg.service;

import java.util.Date;

import javax.inject.Inject;

import org.jpg.domain.BikeVO;
import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;
import org.jpg.persistence.BikeDAO;
import org.jpg.persistence.MemberDAO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO memberDao;
	
	@Inject
	private BikeDAO bikeDao;

	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return memberDao.login(dto);
	}

	@Override
	public void join(MemberVO vo) throws Exception {
		memberDao.insert(vo);
	}

	@Override
	public MemberVO checkRepetition(MemberVO vo) throws Exception {
		return memberDao.selectById(vo);
	}

	@Override
	public Long getTime(int bikenum) throws Exception {
		BikeVO bikeVO = bikeDao.selectByNum(bikenum);
		
		return bikeVO.getUsagedate().getTime();
	}

}
