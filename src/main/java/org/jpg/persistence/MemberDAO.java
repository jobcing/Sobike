package org.jpg.persistence;

import java.util.Date;

import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;

public interface MemberDAO {

	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void insert(MemberVO vo) throws Exception;
	
	public MemberVO selectById(MemberVO vo) throws Exception;
	
	public void rent(String classnum, int bikenum) throws Exception;
	
	public void turnin(int bikenum) throws Exception;
	
	public int selectByClassnum(String classnum) throws Exception;
}
