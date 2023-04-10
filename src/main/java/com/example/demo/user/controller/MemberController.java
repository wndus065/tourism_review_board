package com.example.demo.user.controller;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.comment.service.CommentService;
import com.example.demo.interest.repository.InterestRepository;
import com.example.demo.interest.service.InterestService;
import com.example.demo.requestBoard.service.RequestBoardService;
import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private RequestBoardService reqService;
	
	@Autowired
	private CommentService comService;
	
	@Autowired
	private InterestService interService;

	@GetMapping("/member/list")
	public String list(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<MemberDTO> list = service.getList(page);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", "memberA");
		System.out.println("전체 페이지 수 : " + list.getTotalPages());
		System.out.println("전체 게시물 수 : " + list.getTotalElements());
		System.out.println("현재 페이지 번호 : " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수 : " + list.getNumberOfElements());
		return "/member/list";
	}

	@GetMapping("/member/read")
	public String read(String id, @RequestParam(defaultValue = "0") int page, Model model) {
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("currentPage", "memberA");
		return "/member/read";
	}

	@GetMapping("/member/readMine")
	public void readMine(Principal principal, Model model) {

		String id = principal.getName();

		MemberDTO memberDto = service.read(id);
		model.addAttribute("dto", memberDto);
	}

	@GetMapping("/register")
	public String register() {
		return "/member/register";
	}

	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
		boolean isSuccess = service.register(dto);
		if (isSuccess) {
			return "redirect:/"; // 등록 성공 시 메인화면으로
		} else {
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 회원등록에 실패했습니다.");
			return "redirect:/register";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}

	@PostMapping("/login")
	public String loginPost(@RequestParam("id") String id, @RequestParam("password") String pw,
			RedirectAttributes redirectAttributes, @Autowired HttpServletRequest request, Model model) {
		if (service.read(id) != null && service.read(id).getPassword().equals(pw)) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);

			return "/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "아이디 또는 비밀번호가 옳지 않습니다.");

			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/"; // 로그아웃 후 로그인 페이지로 이동
	}

	@GetMapping("/modify/{id}")
	public String modify(@PathVariable String id, Model model, Principal principal) {
		principal.getName().equals(id);
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
		return "/member/modify";
	}

	@PostMapping("/modify/{id}")
	public String modifyPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("id", dto.getId());
		System.out.println(dto.toString());
		return "redirect:/member/readMine";
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable String id, HttpSession session) {
		System.out.println(id + "회원을 삭제합니다.");

		// 외래키 삭제 단
		reqService.delFkReq(id);
		comService.delFkComM(id);
		interService.delFkInterM(id);
		
		// 외래키 삭제 복
		service.delFkMember(id);

		service.remove(id);
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/home/index")
	public String home(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			// 로그인 상태
			model.addAttribute("isLogin", true);
		} else {
			// 로그아웃 상태
			model.addAttribute("isLogin", false);
		}
		return "home";
	}

	@GetMapping("/idcheck")
	public @ResponseBody HashMap<String, Boolean> idCheck(String id) {
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		MemberDTO memberDto = service.read(id);
		if (memberDto != null) {
			result.put("isDuplicate", true);
		} else {
			result.put("isDuplicate", false);
		}
		return result;
	}

}
