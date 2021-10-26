package com.myBank.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.myBank.dto.Users;
import com.myBank.exception.ExistUserEmailException;
import com.myBank.exception.JoinFailException;
import com.myBank.service.UsersService;

@Controller
public class UserController {
	
	@Autowired  
	UsersService usersService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	// *** 회원가입 페이지 이동 *** //
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join() {
		return "users/joinForm";
	}
	
	// *** 회원가입 (DB insert) *** //
	@RequestMapping(value = "/joinConfirm", method = RequestMethod.POST)
	public String joinSuccess(@ModelAttribute Users users, Model model) throws JoinFailException, ExistUserEmailException {
		usersService.joinUser(users);
		return "redirect:/";
	}
	
	// *** 실시간 이메일 중복검사 (KeyUp 이벤트 + AJAX) *** //
	@RequestMapping(value = "/emailChk", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> emailChk(@RequestParam String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", usersService.selectEmail(email));
		return result;
	}
	
	// *** 로그인 페이지 이동 *** //
	@RequestMapping("/login")
	public String login() {
		return "users/login";
	}
	
	
	// *** 로그인 *** //
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> loginChk(@ModelAttribute Users users, HttpSession session) {

		// 이메일로 전체 정보(컬럼) 검색 - 이메일 존재여부 및 로그인세션 저장목적 //
		Users user = usersService.checkLoginInfo(users.getEmail());
		
		// 로그인 검사 //
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		boolean isValid = false;
		
		// 로그인 실패 처리 - 존재하지 않는 이메일로 검색할 경우 // 
		if(user==null) {
			result.put("isValid", isValid);
			return result;
		} else {
			// 로그인 성공 - 비밀번호 검사 통과할 경우 isValid 키 값으로 true값 전달(json) //
			isValid = passwordEncoder.matches(users.getPassword(), user.getPassword());
			if(isValid) {
				session.setAttribute("loginSession", user);
				result.put("isValid", isValid);
				return result;
			}	
		}
		// 로그인 실패 처리 - 아이디는 올바르게 입력하였으나, 비밀번호가 틀린 경우 //
		result.put("isValid", isValid);
		return result;
		
	}
	
	// *** 카카오톡 로그인 *** //
	@RequestMapping("/kakaoLogin")
	public String kakaoLogin(@RequestParam String nickname ,HttpSession session, @RequestParam("code") String code) {
		usersService.getAccessKakaoToken(code);
		session.setAttribute("kakaoLoginSession", nickname);
		/*
		System.out.println("code : "+ code);
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json; charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant-type", "authorization_code");
		params.add("client_id", "6417a8d5306dd02e7e0977db06db4689");
		params.add("redirect_uri", "http://localhost:8080/controller/");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<MultiValueMap<String,String>>(params,headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		System.out.println("response = "+response);
		*/
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		if(session.getAttribute("loginSession")!=null) {
			session.removeAttribute("loginSession");
		}
		
		if(session.getAttribute("kakaoLoginSession")!=null) {
			session.removeAttribute("kakaoLoginSession");
		}
		
		return "redirect:/";
	}
}
