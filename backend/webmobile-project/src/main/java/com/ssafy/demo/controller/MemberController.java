package com.ssafy.demo.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.demo.dto.MemberDTO;
import com.ssafy.demo.dto.MemberPageDTO;
import com.ssafy.demo.service.MemberService;
import com.ssafy.util.AES256Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("selectAll")
	public List<MemberDTO> selectAll(){
		System.out.println("Members : selectAll");
		return service.selectAll();
	}
	
	@PostMapping("login/{email}/{password}")
	public MemberDTO login(@PathVariable String email, @PathVariable String password) {
		System.out.println(service.selectByEmailAndPassword(email, password));
		return service.selectByEmailAndPassword(email, password);
	}
	
	@PostMapping("insert")
	public int insert(@RequestBody MemberDTO member) {
		int result = service.insert(member);
		System.out.println("SignUp : "+ member.toString());
		return result;
	}
	
	@DeleteMapping("delete/{seq}")
	public int deleteBySeq(@PathVariable int seq) {
		System.out.println("delete : "+seq);
		return service.deleteBySeq(seq);
	}

	@GetMapping("select_email/{email}")
	public MemberDTO selectBySeq(@PathVariable String email) {
		return service.selectByEmail(email);
		
	}
	@PostMapping("update")
	public MemberDTO update(@RequestBody MemberDTO member) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println("update :"+member.toString());
		service.update(member);
		return service.selectByEmail(member.getEmail());
	}
	
	
	@GetMapping("search_name/{name}")
	public List<MemberDTO> searchByName(@PathVariable String name){
		return service.searchByName(name);
	}
	
	@GetMapping("search_email/{email}")
	public List<MemberDTO> searchByEmail(@PathVariable String email){
		return service.searchByEmail(email);
	}
	
	@GetMapping("searchPage_email/{email}/{page}")
	public MemberPageDTO searchPageByEmail(@PathVariable String email, @PathVariable String page) {
		return service.makePageMemberByEmail(email, page, 10);
	}
	
	@GetMapping("findPassword/{email}")
	public String mailSender(@PathVariable String email) throws AddressException, MessagingException, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		email = email.trim();
		System.out.println("회원정보 :"+email);
		MemberDTO member = service.selectByEmail(email);
		if(member !=null && member.getEmail().equals(email)) {
			// 네이버일 경우 smtp.naver.com 을 입력합니다.
			// Google일 경우 smtp.gmail.com 을 입력합니다.
			String host = "smtp.naver.com";
			final String username = "qkrtlswhd"; // 네이버 아이디를 입력해주세요. @nave.com은 입력하지 마시구요.
			final String password = "dkffkels32"; // 네이버 이메일 비밀번호를 입력해주세요.
			int port = 465; // 포트번호
			// 메일 내용
			String recipient = member.getEmail(); // 받는 사람의 메일주소를 입력해주세요. starlight747@naver.com
			String subject = member.getEmail()+" 회원 비밀번호"; // 메일 제목 입력해주세요. 
			String body = member.getEmail()+"님의 비밀번호는 :"+(new AES256Util()).decrypt(member.getPassword()) +" 입니다.";
			// 메일 내용 입력해주세요.
			Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
			// SMTP 서버 정보 설정
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host); // Session 생성
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = username;
				String pw = password;

				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(true); // for debug
			Message mimeMessage = new MimeMessage(session); // MimeMessage 생성
			mimeMessage.setFrom(new InternetAddress("qkrtlswhd@naver.com")); // 발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀
																			// 주소를 다 작성해주세요.
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자셋팅 //.TO 외에 .CC(참조)
																								// .BCC(숨은참조) 도 있음
			mimeMessage.setSubject(subject); // 제목셋팅 
			mimeMessage.setText(body); //내용셋팅
			Transport.send(mimeMessage); // javax.mail.Transport.send() 이용
			return "이메일 전송에 성공하였습니다.";
		}else {
			return "회원정보가 일치하지 않습니다";
		}
	}
}
