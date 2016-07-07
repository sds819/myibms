package com.cesecsh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	@RequestMapping("")
	public String login(HttpServletRequest request,Model model){
		return "layout";
	}

}
