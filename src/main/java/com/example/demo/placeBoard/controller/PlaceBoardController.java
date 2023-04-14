package com.example.demo.placeBoard.controller;

import java.security.Principal;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.comment.service.CommentService;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.service.InterestService;
import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.service.MapService;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.service.PlaceBoardService;


@Controller
@RequestMapping("/placeboard")
public class PlaceBoardController {

	@Autowired
	private PlaceBoardService service;

	@Autowired
	private CommentService commentService;

	@Autowired
	private MapService mapService;
	
	@Autowired
	private InterestService interService;
	

	// 콘텐츠 검색
    @GetMapping("/search/content")
    public String searchByContent(Model model, @RequestParam("keyword") String content) {
        List<PlaceBoard> placeBoards = service.searchByContent(content);
        model.addAttribute("placeBoards", placeBoards);
        return "placeboard/list";
    }

    // 제목 검색
    @GetMapping("/search/title")
    public String searchByTitle(Model model, @RequestParam("keyword") String title) {
        List<PlaceBoard> placeBoards = service.searchByTitle(title);
        model.addAttribute("placeBoards", placeBoards);
        return "placeboard/list";
    }

//    // 장소 검색
//    @GetMapping("/search/place")
//    public String searchByPlace(Model model, @RequestParam("place") MapEntity place) {
//        MapEntity mapEntity = MapEntity.builder().place(dto.getPlace()).build();
//        List<PlaceBoard> placeBoards = service.searchByPlace(place);
//        model.addAttribute("placeBoards", placeBoards);
//        return "placeboard/list";
//    }
//
//    // 작성자 검색
//    @GetMapping("/search/writer")
//    public String searchByWriter(Model model, @RequestParam("writer") Member writer) {
//        Member member = Member.builder().id(writer).build();
//        List<PlaceBoard> placeBoards = service.searchByWriter(writer);
//        model.addAttribute("placeBoards", placeBoards);
//        return "placeboard/list";
//    }


	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model, Principal principal, @RequestParam(defaultValue = "") String place) { // 파라미터 추가
		String id = principal.getName();
		Page<PlaceBoardDTO> list = service.getList(page);
		List<Interest> interList = interService.getInterestByMemId(id);
		model.addAttribute("interList", interList);
		model.addAttribute("logInid", id);	
		model.addAttribute("list", list);
		model.addAttribute("currentPage", "placeboard");
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
	public void read(int no, @RequestParam(defaultValue = "0") int page, Model model, org.springframework.security.core.Authentication authentication, Principal principal) {
	    String id = principal.getName();
	    PlaceBoardDTO dto = service.read(no);
	    List<Interest> interList = interService.getInterestByMemId(id);
	    model.addAttribute("interList", interList);
	    
	    // 이미지 경로를 가져와 model에 추가
	    String imgPath = dto.getImgPath();
	    if (imgPath != null) {
	        String[] imgPaths = imgPath.split(",");
	        model.addAttribute("imgPaths", imgPaths);
	    }

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

		// 왜래키 삭제
		interService.delFkInter(no);
		commentService.delFkCom(no);
		
		service.remove(no);
		return "redirect:/placeboard/list";
	}
}
