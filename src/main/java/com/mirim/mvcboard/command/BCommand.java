package com.mirim.mvcboard.command;

import org.springframework.ui.Model;

public interface BCommand {		// 공통적인 부분 묶어서 부름, 1개만 작성

	void execute(Model model);		// modal을 통째로 불러옴
		
	
}
