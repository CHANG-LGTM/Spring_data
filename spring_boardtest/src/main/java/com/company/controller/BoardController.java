package com.company.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dto.BoardDTO;
import com.company.dto.PageDTO;
import com.company.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // /board 경로에 대한 요청을 처리하는 컨트롤러
public class BoardController {
	
	private final BoardService boardService; // BoardService 주입

	@GetMapping("/save")
	public String saveForm() {
		// 글 작성 폼 페이지로 이동
		return "save";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) {
		// 글 저장 처리 후, 글 목록 페이지로 리다이렉트
		int saveResult = boardService.save(boardDTO);
		return "redirect:/board/paging"; // 저장 후 페이징된 목록으로 리다이렉트
	}

	@GetMapping("/")
	public String findAll(Model model) {
		// 모든 게시글 목록을 가져와서 모델에 추가하고 리스트 페이지로 이동
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "list";
	}	

	@GetMapping
	public String findById(@RequestParam("id") Long id,
	                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	                       Model model) {
		// 게시글 조회 시 조회수를 증가시키고, 상세 페이지로 이동
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", page); // 페이지 번호도 함께 전달
		return "detail"; // 상세 보기 페이지로 이동
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		// 해당 게시글을 삭제한 후 게시글 목록으로 리다이렉트
		boardService.delete(id);
		return "redirect:/board/"; // 삭제 후 글 목록으로 리다이렉트
	}

	@GetMapping("/update")
	public String updateForm(@RequestParam("id") Long id, Model model) {
		// 수정할 게시글의 정보를 가져와서 수정 폼으로 전달
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "update"; // 수정 폼 페이지로 이동
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		// 게시글 수정 처리 후, 수정된 글의 상세 페이지로 이동
		boardService.update(boardDTO);
		BoardDTO dto = boardService.findById(boardDTO.getId());
		model.addAttribute("board", dto);
		return "detail"; // 수정된 게시글의 상세 페이지로 이동
	}

	// /board/paging?page=2
    // 처음 페이지 요청은 1페이지를 보여줌
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        // 페이지 번호에 맞는 게시글 목록을 가져와서 페이징 처리
        List<BoardDTO> pagingList = boardService.pagingList(page);
        PageDTO pageDTO = boardService.pagingParam(page); // 페이지 관련 정보 가져오기
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO); // 페이징 정보를 모델에 추가
        return "paging"; // 페이징된 목록을 보여주는 페이지로 이동
    }  
}
