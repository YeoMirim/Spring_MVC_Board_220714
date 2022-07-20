package com.mirim.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mirim.mvcboard.dao.BDao;

public class BReplyCommand implements BCommand {	// write 양식과 비슷함

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();		// 안에 든거 뽑아냄(arrayList와 비슷 map), Object는 몽땅 다 가져올 수 있음, 
		HttpServletRequest request = (HttpServletRequest) map.get("request");		
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		
		BDao dao = new BDao();		// 객체 생성
		dao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);   // 값을 씀
		
	}
}
