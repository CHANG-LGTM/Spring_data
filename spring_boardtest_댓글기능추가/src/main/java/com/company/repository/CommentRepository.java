package com.company.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.dto.CommentDTO;

import lombok.RequiredArgsConstructor;

@Repository // 이 클래스가 Spring의 Repository 역할을 함 (데이터베이스와의 상호작용 담당)
@RequiredArgsConstructor // Lombok 어노테이션으로, final 필드에 대한 생성자를 자동으로 생성
public class CommentRepository {
    
    private final SqlSessionTemplate sql; // MyBatis의 SqlSessionTemplate을 사용하여 DB 접근

    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save", commentDTO); // "Comment.save" 쿼리 ID를 사용하여 댓글을 DB에 저장
    }

    public List<CommentDTO> findAll(Long boardId) {
        return sql.selectList("Comment.findAll", boardId); // "Comment.findAll" 쿼리 ID를 사용하여 특정 게시글에 대한 댓글 목록을 조회
    }
}
