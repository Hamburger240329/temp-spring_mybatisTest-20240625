package com.gyojincompany.mybatis.dao;

import java.util.ArrayList;

import com.gyojincompany.mybatis.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String bcontent);
	public ArrayList<BoardDto> boardListDao();
	
}
