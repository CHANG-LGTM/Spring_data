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
import com.company.dto.CommentDTO;
import com.company.dto.PageDTO;
import com.company.service.BoardService;
import com.company.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller // 이 클래스가 Spring MVC의 컨트롤러 역할을 한다는 것을 명시
@RequiredArgsConstructor // Lombok 어노테이션으로, final 필드에 대한 생성자를 자동으로 생성
@RequestMapping("/board") // "/board" 경로에 대한 요청을 처리하는 컨트롤러
public class BoardController {

    private final BoardService boardService; // BoardService 의존성 주입
    private final CommentService commentService; // CommentService 의존성 주입

    @GetMapping("/save") // 글 작성 폼 페이지를 보여주는 요청
    public String saveForm() {
        return "save"; // 글 작성 폼 뷰로 이동
    }

    @PostMapping("/save") // 글 작성 폼에서 제출된 데이터를 처리
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO); // 글 저장 처리
        return "redirect:/board/paging"; // 저장 후 페이징된 글 목록으로 리다이렉트
    }

    @GetMapping("/") // 모든 게시글 목록을 가져오는 요청
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll(); // 모든 게시글 리스트 조회
        model.addAttribute("boardList", boardDTOList); // 조회된 게시글 리스트를 모델에 추가
        return "list"; // 게시글 목록 뷰로 이동
    }

    @GetMapping // 게시글 상세 보기 요청
    public String findById(@RequestParam("id") Long id, 
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
                           Model model) {
        boardService.updateHits(id); // 게시글 조회수 증가
        BoardDTO boardDTO = boardService.findById(id); // 게시글 상세 정보 조회
        model.addAttribute("board", boardDTO); // 게시글 정보 모델에 추가
        model.addAttribute("page", page); // 페이지 번호 모델에 추가
        List<CommentDTO> commentDTOList = commentService.findAll(id); // 해당 게시글에 작성된 댓글 리스트 조회
        model.addAttribute("commentList", commentDTOList); // 댓글 리스트 모델에 추가
        return "detail"; // 게시글 상세 페이지로 이동
    }

    @GetMapping("/delete") // 게시글 삭제 요청
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id); // 해당 게시글 삭제 처리
        return "redirect:/board/"; // 삭제 후 게시글 목록으로 리다이렉트
    }

    @GetMapping("/update") // 게시글 수정 폼 요청
    public String updateForm(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id); // 수정할 게시글 조회
        model.addAttribute("board", boardDTO); // 수정할 게시글 정보 모델에 추가
        return "update"; // 게시글 수정 폼 페이지로 이동
    }

    @PostMapping("/update") // 수정된 게시글 데이터를 처리하는 요청
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO); // 게시글 수정 처리
        BoardDTO dto = boardService.findById(boardDTO.getId()); // 수정된 게시글 정보 조회
        model.addAttribute("board", dto); // 수정된 게시글 정보를 모델에 추가
        return "detail"; // 수정된 게시글의 상세 페이지로 이동
    }

    @GetMapping("/paging") // 게시글 페이징 처리를 위한 요청
    public String paging(Model model, 
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        List<BoardDTO> pagingList = boardService.pagingList(page); // 페이지 번호에 맞는 게시글 목록 조회
        PageDTO pageDTO = boardService.pagingParam(page); // 페이지 관련 정보 조회
        model.addAttribute("boardList", pagingList); // 페이징된 게시글 리스트 모델에 추가
        model.addAttribute("paging", pageDTO); // 페이징 정보 모델에 추가
        return "paging"; // 페이징된 게시글 목록 페이지로 이동
    }
}
