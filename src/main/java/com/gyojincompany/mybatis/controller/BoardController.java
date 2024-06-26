package com.gyojincompany.mybatis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.mybatis.dao.BoardDao;
import com.gyojincompany.mybatis.dao.MemberDao;
import com.gyojincompany.mybatis.dto.MemberDto;

@Controller
public class BoardController {
	
	@Autowired //의존 자동 주입 DI
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/write")
	public String write(HttpSession session, Model model) {
		
		//세션에서 sessionid 값 가져오기(세션 매개변수 선언 필수)
		String sessionid = (String) session.getAttribute("sessionid");
		
		if(sessionid == null) {//로그인 안된 상태
			model.addAttribute("loginFail", "글쓰기는 로그인 상태에서만 가능합니다.");
			return "login";
		} else {//로그인 된 상태
			
			MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
			MemberDto memberDto = memberDao.searchDao(sessionid);
			
			model.addAttribute("mid", sessionid);
			model.addAttribute("mname", memberDto.getMname());
			
			return "write_form";
		}
	}
	
	@RequestMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		boardDao.writeDao(request.getParameter("bid"), request.getParameter("bname"), request.getParameter("btitle"), request.getParameter("bcontent"));
		
		return "redirect:list";
	}
	
	
	
	
	
	

}
