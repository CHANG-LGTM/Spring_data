package com.company.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.company.dto.BoardDTO;

import lombok.RequiredArgsConstructor;

@Service // 이 클래스는 서비스 계층의 일부로 처리되는 컴포넌트
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
public class BoardRepository {

	private final SqlSessionTemplate sql; // MyBatis SQL 세션 템플릿 주입
	
	// 게시글 저장
	public int save(BoardDTO boardDTO) {
		return sql.insert("Board.save", boardDTO); // SQL 쿼리 실행
	}

	// 모든 게시글 목록 조회
	public List<BoardDTO> findAll() {
		return sql.selectList("Board.findAll"); // SQL 쿼리 실행
	}

	// 게시글 ID로 조회
	public BoardDTO findById(Long id) {
		return sql.selectOne("Board.findById", id); // SQL 쿼리 실행
	}

	// 게시글 조회수 증가
	public void updateHits(Long id) {
		sql.update("Board.updateHits", id); // SQL 쿼리 실행
	}

	// 게시글 삭제
	public void delete(Long id) {
		sql.delete("Board.delete", id); // SQL 쿼리 실행
	}

	// 게시글 수정
	public void update(BoardDTO boardDTO) {
		sql.update("Board.update", boardDTO); // SQL 쿼리 실행
	}

	// 페이징을 위한 게시글 목록 조회
	public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
		return sql.selectList("Board.pagingList", pagingParams); // SQL 쿼리 실행
	}

	// 전체 게시글 수 조회
	public int boardCount() {
		return sql.selectOne("Board.boardCount"); // SQL 쿼리 실행
	}
}
