package com.company.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dto.CommentDTO;
import com.company.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller // 이 클래스가 Spring MVC의 컨트롤러 역할을 한다는 것을 명시
@RequiredArgsConstructor // Lombok 어노테이션으로, final 필드에 대한 생성자를 자동 생성
@RequestMapping("/comment") // "/comment" 경로로 들어오는 요청을 처리하는 컨트롤러
public class CommentController {

    private final CommentService commentService; // 댓글 서비스 의존성 주입

    // 댓글 저장 및 해당 게시글에 대한 모든 댓글 리스트를 반환하는 메소드
    @PostMapping("/save") // "/save" 경로로 POST 요청을 처리
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO); // 로그로 전달된 댓글 데이터 출력
        commentService.save(commentDTO); // 댓글 저장
        // 해당 게시글에 작성된 댓글 리스트를 가져와서 반환
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return commentDTOList; // 댓글 목록을 JSON 형식으로 반환
    }
}
