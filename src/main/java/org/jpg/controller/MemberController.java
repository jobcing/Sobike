package org.jpg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;
import org.jpg.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application User.
 */

@Controller
@RequestMapping("/user/*")
public class MemberController {

	@Inject
	private MemberService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(Model model){
		return "home";
	}
	
	/*
	// JSON을 주고 받는 것 까지는 구현완료.
	// @RequestBody LoginDTO
	@RequestMapping(value = "/loginPost", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody MemberVO loginPOST(LoginDTO dto, HttpSession session, Model model)
		throws Exception{
		
		logger.info("member login post..............");
		
		MemberVO vo = service.login(dto);
		
		if(vo == null){
			return null;
		}
		
		// return JSONObject
		model.addAttribute("memberVO", vo);
		
		return vo;
	}
	*/
	
	// 인터셉터 설정 안되있는 메소드
	@RequestMapping(value = "/loginPo", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, Object> loginPO(String classnum, String regnum, Model model)
		throws Exception{
		
		logger.info("member login post..............");
		
		MemberVO vo = service.login(new LoginDTO(classnum, regnum));
		
		Map<String, Object> JSONObject = new HashMap<String, Object>();
		
		if(vo == null){
			JSONObject.put("CHECK", "FAIL");
		} else if(vo.getStatus()){
			JSONObject.put("CHECK", "USAGE");
			
			JSONObject.put("TIME", service.getTime(vo.getBikenum()));
		} else{
			JSONObject.put("CHECK", "SUCCESS");
		}
		
		// return JSONObject
		model.addAttribute("memberVO", vo);
		
		return JSONObject;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logoutGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ResponseEntity<String> joinGET(@RequestBody MemberVO vo, Model model) throws Exception{
		logger.info("member join page get..............");
		
		ResponseEntity<String> entity = null;
		
		try {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(@ModelAttribute("vo") MemberVO vo, RedirectAttributes rttr) throws Exception{
		logger.info("member join page post..............");
		
		service.join(vo);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		// 브라우저까지 전송되긴하지만, URI상에는 보이지 않는 데이터로 전송된다.
		
		return "redirect:/";
		// 회원가입에 성공하면 홈페이지로 이동하도록 설정
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<MemberVO> checkID(@RequestBody MemberVO vo) {

		ResponseEntity<MemberVO> entity = null;
		
		try {
			MemberVO member = service.checkRepetition(vo);
			entity = new ResponseEntity<MemberVO>(member, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<MemberVO>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
}
