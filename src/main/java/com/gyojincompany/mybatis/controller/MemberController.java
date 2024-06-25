package com.gyojincompany.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.mybatis.dao.MemberDao;

@Controller
public class MemberController {
	
	@Autowired //의존 자동 주입 DI
	private SqlSession sqlSession;
	
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		memberDao.joinMemberDao(mid, mpw, mname, memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("mname", mname);
		
		return "joinOk";
	}

}
