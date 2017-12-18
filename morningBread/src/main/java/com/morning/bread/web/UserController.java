package com.morning.bread.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.morning.bread.domain.User;
import com.morning.bread.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// 회원가입 페이지
	@GetMapping("/createForm")
	public String createForm(User user, Model model) {
		return "/user/form";
	}
	
	// 회원가입
	@PostMapping("/create")
	public String create(User user, Model model, HttpSession session) {
		userRepository.save(user);
		// 로그인 여부를 확인 해서 로그인 페이지로 보낸다.
		if(session.getAttribute("sessionedUser") == null) {
			return "redirect:/user/loginForm";
		}
		return "redirect:/user/list";
	}
	
	// 회원조회
	@GetMapping("/list")
	public String list(Model model, HttpSession session) {
		// 로그인 여부를 확인 해서 로그인 페이지로 보낸다.
		if(session.getAttribute("sessionedUser") == null) {
			return "redirect:/user/loginForm";
		}
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	// 회원정보 상세조회
	@GetMapping("/{id}/show")
	public String show(@PathVariable Long id, Model model, HttpSession session) {
		// 로그인 여부를 확인 해서 로그인 페이지로 보낸다.
		if(session.getAttribute("sessionedUser") == null) {
			return "redirect:/user/loginForm";
		}
				
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/show";
	}
	
	// 회원정보 수정
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {
		System.out.println("update session : " + session.getAttribute("sessionedUser") );
		// 로그인 여부를 확인 해서 로그인 페이지로 보낸다.
		if(session.getAttribute("sessionedUser") == null) {
			return "redirect:/user/loginForm";
		}
		
		userRepository.findOne(id).update(updateUser);
		userRepository.save(userRepository.findOne(id));
		return "redirect:/user/list";
	}
	
	
	// 로그인 페이지
	@GetMapping("loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			return "/user/login";
		}
		
		System.out.println("login userId : " + userId);
		System.out.println("login password : " + password);
		System.out.println("login user : " + user);
		
		if(!user.getPassword().equals(password)) {
			return "/user/login";
		}
		
//		if(!user.matchPassword(password)) {
//			return "redirect:/users/loginForm";
//		}
//		
		// 로그인 정보를 세션에 담는다.
		session.setAttribute("sessionedUser", user);
		
		return "redirect:/contact/main";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}
}
