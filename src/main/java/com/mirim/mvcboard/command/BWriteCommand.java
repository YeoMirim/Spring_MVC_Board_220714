package com.mirim.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mirim.mvcboard.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {		// interface를 상속받아 override
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();		// 안에 든거 뽑아냄(arrayList와 비슷 map), Object는 몽땅 다 가져올 수 있음, 
		HttpServletRequest request = (HttpServletRequest) map.get("request");		
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
	
		BDao dao = new BDao();		// 객체 생성
		dao.write(bname, btitle, bcontent);   // 값을 씀
	}

}
