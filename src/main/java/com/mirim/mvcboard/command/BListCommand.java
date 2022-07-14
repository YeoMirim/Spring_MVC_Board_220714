package com.mirim.mvcboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.mirim.mvcboard.dao.BDao;
import com.mirim.mvcboard.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		BDao dao = new BDao();
		
		ArrayList<BDto> btos = dao.list();
		
		model.addAttribute("list", btos);
	}

}
