package com.gyojincompany.mybatis.dao;

import com.gyojincompany.mybatis.dto.MemberDto;

public interface MemberDao {
	
	public void joinMemberDao(String mid, String mpw, String mname, String memail);//회원 가입 메소드
	public int loginDao(String mid, String mpw);//로그인 체크 메소드
	public MemberDto searchDao(String mid);//회원 아이디로 정보 조회 메소드
}
