package com.gyojincompany.mybatis.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.mybatis.dao.MemberDao;
import com.gyojincompany.mybatis.dto.MemberDto;

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
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, HttpSession session, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		int loginFlag = memberDao.loginDao(mid, mpw);
		if (loginFlag == 1) { //참이면 로그인 성공
			session.setAttribute("sessionid", mid);
			model.addAttribute("loginid", mid);
			return "loginOk";
		} else { //로그인 실패
			model.addAttribute("loginFail", "아이디와 비밀번호를 다시 확인 후 로그인해주세요.");
			return "login";
		}
	}
	
	@RequestMapping(value = "/search")
	public String search() {
		return "search";
	}
	
	@RequestMapping(value = "/searchOk")
	public String searchOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		MemberDto memberDto = memberDao.searchDao(mid);		
		
		if(memberDto != null) {
			model.addAttribute("memberDto", memberDto);
		} else {			
			model.addAttribute("searchFail", "조회하신 아이디는 존재하지 않습니다.");
		}
		
		return "searchOk";
	}
	
	@RequestMapping(value = "/memberlist")
	public String memberlist(Model model) {
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		ArrayList<MemberDto> memberDtos = memberDao.memberlistDao();
		int totalMember = memberDao.totalMemberDao();//총 회원수 반환
		
		model.addAttribute("memberList", memberDtos);
		model.addAttribute("total", totalMember);
		
		return "memberlist";
	}
	
	@RequestMapping(value = "/delete")
	public String delete() {
		return "delete";
	}
	
	@RequestMapping(value = "/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		int success = memberDao.deleteMemberDao(mid);
		
		if(success == 1) {//참이면 성공
			return "redirect:memberlist";
		} else {//실패
			model.addAttribute("deleteFail", "존재하지 않는 회원입니다. 다시 확인하세요.");
			return "delete";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();//로그아웃->세션삭제
		
		return "redirect:login";
	}
	
	@RequestMapping(value = "/checkid")
	public String checkid(HttpServletRequest request, Model model) {
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		int idFlag = memberDao.checkIdDao(request.getParameter("checkId"));
		
		model.addAttribute("idFlag", idFlag);
		
		return "check_id";
	}
	
	
	
	
	

}
