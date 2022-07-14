package com.mirim.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mirim.mvcboard.dao.BDao;
import com.mirim.mvcboard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {  // request 객체가 들어있음
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();	// Mapping 작업 (model로 온걸 request 객체로 다시 뽑아줌)
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bid = request.getParameter("bid");
//		System.out.println("게시판 번호 확인 : " + bid);
		
		BDao dao = new BDao();
		BDto dto= dao.contentView(bid);
		
		model.addAttribute("dto", dto);	// 모델에 실었음
		
	}
}
