package com.gyojincompany.mybatis.dao;

import java.util.ArrayList;

import com.gyojincompany.mybatis.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String bcontent);//글쓰기
	public ArrayList<BoardDto> boardListDao();//글 목록
	public BoardDto contentViewDao(String bnum);//글 번호로 내용 조회
	public void modifyDao(String btitle, String bcontent, String bnum);//글 수정
}
