package com.mirim.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mirim.mvcboard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
	
		Map<String, Object> map = model.asMap();	// Mapping 작업 (model로 온걸 request 객체로 다시 뽑아줌)
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BDao dao = new BDao();
		dao.modify(bid, bname, btitle, bcontent);	// 수정하면 끝이라 model을 실어줄 필요 X
		
	}
}
