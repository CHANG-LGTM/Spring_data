package com.company.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.dto.CommentDTO;
import com.company.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service // 이 클래스가 서비스 레이어임을 나타내는 어노테이션
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해주는 Lombok 어노테이션
public class CommentService {
    private final CommentRepository commentRepository; // CommentRepository 의존성 주입

    // 댓글 저장 메소드
    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO); // 댓글을 저장하는 메소드 호출
    }

    // 게시글에 대한 모든 댓글을 조회하는 메소드
    public List<CommentDTO> findAll(Long boardId) {
         return commentRepository.findAll(boardId); // 게시글 ID로 댓글 목록을 조회
    }
}
