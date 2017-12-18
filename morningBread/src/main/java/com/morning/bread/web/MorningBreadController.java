package com.morning.bread.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MorningBreadController {
	@GetMapping("/")
	public String index(HttpSession session) {
		System.out.println("session : " + session.getAttribute("sessionedUser") );
		return "/index";
	}
}
