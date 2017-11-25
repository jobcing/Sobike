package org.jpg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jpg.domain.BikeVO;
import org.jpg.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application User.
 */

@Controller
@RequestMapping("/bike/*")
public class BikeController {

	@Inject
	private BikeService service;

	private static final Logger logger = LoggerFactory.getLogger(BikeController.class);
	
	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<BikeVO>> listGET(Model model) throws Exception{
		Map<String, List<BikeVO>> JSONObject = new HashMap<String, List<BikeVO>>();
		
		List<BikeVO> list = new ArrayList<>();
		
		list = service.showList();
		
		JSONObject.put("list", list);
		
		return JSONObject;
	}
	
	@RequestMapping(value = "/rent", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> rentPost(String bikeno, String date, String classnum, Model model)
			throws Exception{
			
			logger.info("rent post..............");

			Map<String, Object> JSONObject = new HashMap<String, Object>();
			
			int bikenum = new Integer(bikeno);
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = transFormat.parse(date);
			
			BikeVO vo = service.selectByNum(bikenum);
			
			if(vo.getStatus()){ // 사용 중일 경우
				JSONObject.put("CHECK", "FAIL");
			} else{
				service.rent(bikenum, startDate, classnum);
				JSONObject.put("CHECK", "SUCCESS");
			}
			
			// bikenum을 사용해서 usage와 date를 update한다.
			// 컨트롤러에서는 단지 service.use()
			// 서비스에서 사용자와 바이크 연동시킨다.
			// 서비스에 넘겨줄 건 bikenum, startDate, classnum
			
			// return JSONObject
			model.addAttribute("memberVO", vo);
			
			return JSONObject;
		}
	
	@RequestMapping(value = "/turnin", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> returnPost(String classnum, String location, Model model)
			throws Exception{
			
			logger.info("turnin post..............");

			Map<String, Object> JSONObject = new HashMap<String, Object>();
			
			int bikenum = service.selectByClassnum(classnum);
			
			BikeVO vo = service.selectByNum(bikenum);
			
			int loc = 0;
			
			if(location.equals("제 1공학관")){
				loc = 1;
			} else if(location.equals("제 2공학관")){
				loc = 2;
			} else if(location.equals("제 3공학관")){
				loc = 3;
			}
			
			if(!vo.getStatus()){ // 사용 중이 아닌 경우
				JSONObject.put("CHECK", "FAIL");
			} else{
				service.turnin(bikenum, loc);
				JSONObject.put("CHECK", "SUCCESS");
			}
			
			// bikenum을 사용해서 usage와 date를 update한다.
			// 컨트롤러에서는 단지 service.use()
			// 서비스에서 사용자와 바이크 연동시킨다.
			// 서비스에 넘겨줄 건 bikenum, startDate, classnum
			
			// return JSONObject
			model.addAttribute("memberVO", vo);
			
			return JSONObject;
		}
}


