package com.example.demo.placeBoard.controller;

import java.security.Principal;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.CommentService;
import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.service.MapService;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.requestBoard.dto.RequestBoardDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/placeboard")
public class PlaceBoardController {

	@Autowired
	private PlaceBoardService service;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MapService mapService;
	

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) { //파라미터 추가
		Page<PlaceBoardDTO> list = service.getList(page);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", "placeboard");
		System.out.println("전체 페이지 수: " + list.getTotalPages());
		System.out.println("전체 게시물 수: " + list.getTotalElements());
		System.out.println("현재 페이지 번호: " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수: " + list.getNumberOfElements());
	}

	@GetMapping("/register")
	public String register(Principal principal, Model model) {
	    String id = principal.getName();
	    if (id == null) {
	        // 로그인 되어 있지 않은 경우 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }
	    PlaceBoardDTO dto = new PlaceBoardDTO();
	    dto.setWriter(id);
	    model.addAttribute("dto", dto);
	    model.addAttribute("currentPage", "placeboard");
	    
	    
		//관광지 목록 보내기
//		Page<MapDTO> result  = mapService.getlist(0);
//		List<MapDTO> list = result.getContent();
	    List<MapDTO> list = mapService.pickPlace();
		
		model.addAttribute("placelist", list);
	    return "/placeboard/register";
	}

	@PostMapping("/register")
	public String registerPost(PlaceBoardDTO dto, RedirectAttributes redirectAttributes) {
	    int no = service.register(dto);
	    redirectAttributes.addFlashAttribute("msg", no);
	    return "redirect:/placeboard/list";
	}

	@GetMapping("/read")
	public void read(int no, @RequestParam(defaultValue = "0") int page, Model model, org.springframework.security.core.Authentication authentication) {
	    PlaceBoardDTO dto = service.read(no);
	    model.addAttribute("dto", dto);
	    model.addAttribute("page", page);
	    model.addAttribute("currentPage", "placeboard");
	    model.addAttribute("user", authentication.getName()); // 인증된 사용자의 이름을 추가
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("no") int no, Model model, Principal principal) {
	    String id = principal.getName(); // 사용자 ID 가져오기
	    PlaceBoardDTO dto = service.read(no);
	    if (dto.getWriter().equals(id)) {
	        model.addAttribute("dto", dto);
	        return "placeboard/modify";
	    } else {
	        return "redirect:/placeboard/read?no=" + no;
	    }
	}

	@PostMapping("/modify")
	public String modifyPost(PlaceBoardDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/placeboard/read";
	}

	@GetMapping("/remove")
	public String removePost(int no) {
		service.remove(no);
		return "redirect:/placeboard/list";
	}
}
