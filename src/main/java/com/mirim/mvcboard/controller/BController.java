package com.mirim.mvcboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mirim.mvcboard.command.BCommand;
import com.mirim.mvcboard.command.BContentCommand;
import com.mirim.mvcboard.command.BListCommand;
import com.mirim.mvcboard.command.BWriteCommand;

@Controller
public class BController {			//command만 호출
	
	BCommand command;
	
	@RequestMapping(value="/write_form")
	public String write_form() {
		
		return "write_form";
	}
	
	@RequestMapping(value="/write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);		// request 객체를 modal 담아서 command에 보내버림		
		
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";	// redirect는 list 요청을 다시 돌려서 보여줌(reload)
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {		// 새로운 값을 넣어줄건 없으므로 model만 있으면 됨
		
		command = new BListCommand();
		command.execute(model);		// model에 list(ArrayList<BDto> dtos)가 탑재 
		
		return "list";
	}
	
	@RequestMapping(value="/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);		// request 객체를 modal 담아서 command에 보내버림		
		
		command = new BContentCommand();
		command.execute(model);		// 실행하면 model에 dto란 이름으로 탑재 완료
		
		return "content_view";
	}
}
