package com.morning.bread.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.morning.bread.domain.Contact;
import com.morning.bread.domain.ContactRepository;

@Controller
@RequestMapping("/contact")
public class ContactController {
	
//	private List<Contact> contacts = new ArrayList();
	
	@Autowired
	private ContactRepository contactRepository;

	@PostMapping("/create")
	public String create(Contact contact, Model model) {
//		contacts.add(contact);
//		model.addAttribute("contacts",contacts);
		contactRepository.save(contact);
		return "redirect:/";
	}
	
	//메인화면
	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
		// 로그인 여부를 확인 해서 로그인 페이지로 보낸다.
		if(session.getAttribute("sessionedUser") == null) {
			return "/user/loginForm";
		}
		// 문의사항
//		model.addAttribute("contacts", contacts);
		model.addAttribute("contacts", contactRepository.findAll());
		return "main";
	}
	
}
