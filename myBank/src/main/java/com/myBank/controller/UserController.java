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
	
	
	// *** ȸ������ ������ �̵� *** //
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join() {
		return "users/joinForm";
	}
	
	// *** ȸ������ (DB insert) *** //
	@RequestMapping(value = "/joinConfirm", method = RequestMethod.POST)
	public String joinSuccess(@ModelAttribute Users users, Model model) throws JoinFailException, ExistUserEmailException {
		usersService.joinUser(users);
		return "redirect:/";
	}
	
	// *** �ǽð� �̸��� �ߺ��˻� (KeyUp �̺�Ʈ + AJAX) *** //
	@RequestMapping(value = "/emailChk", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> emailChk(@RequestParam String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", usersService.selectEmail(email));
		return result;
	}
	
	// *** �α��� ������ �̵� *** //
	@RequestMapping("/login")
	public String login() {
		return "users/login";
	}
	
	
	// *** �α��� *** //
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> loginChk(@ModelAttribute Users users, HttpSession session) {

		// �̸��Ϸ� ��ü ����(�÷�) �˻� - �̸��� ���翩�� �� �α��μ��� ������� //
		Users user = usersService.checkLoginInfo(users.getEmail());
		
		// �α��� �˻� //
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		boolean isValid = false;
		
		// �α��� ���� ó�� - �������� �ʴ� �̸��Ϸ� �˻��� ��� // 
		if(user==null) {
			result.put("isValid", isValid);
			return result;
		} else {
			// �α��� ���� - ��й�ȣ �˻� ����� ��� isValid Ű ������ true�� ����(json) //
			isValid = passwordEncoder.matches(users.getPassword(), user.getPassword());
			if(isValid) {
				session.setAttribute("loginSession", user);
				result.put("isValid", isValid);
				return result;
			}	
		}
		// �α��� ���� ó�� - ���̵�� �ùٸ��� �Է��Ͽ�����, ��й�ȣ�� Ʋ�� ��� //
		result.put("isValid", isValid);
		return result;
		
	}
	
	// *** īī���� �α��� *** //
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
