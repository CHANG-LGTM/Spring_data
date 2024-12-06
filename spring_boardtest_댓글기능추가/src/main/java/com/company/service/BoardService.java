package com.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.dto.BoardDTO;
import com.company.dto.PageDTO;
import com.company.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service // 이 클래스는 서비스 계층을 처리하는 컴포넌트
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
public class BoardService {
	
	private final BoardRepository boardRepository; // BoardRepository 주입

	// 게시글 저장
	public int save(BoardDTO boardDTO) {
		return boardRepository.save(boardDTO); // 게시글 저장 후 결과 반환
	}

	// 모든 게시글 조회
	public List<BoardDTO> findAll() {
		return boardRepository.findAll(); // 모든 게시글 조회
	}

	// 게시글 ID로 조회
	public BoardDTO findById(Long id) {
		return boardRepository.findById(id); // ID로 게시글 조회
	}

	// 게시글 조회수 증가
	public void updateHits(Long id) {
		boardRepository.updateHits(id); // 조회수 증가
	}

	// 게시글 삭제
	public void delete(Long id) {
		boardRepository.delete(id); // 게시글 삭제
	}

	// 게시글 수정
	public void update(BoardDTO boardDTO) {
		boardRepository.update(boardDTO); // 게시글 수정
	}

	// 페이징된 게시글 목록 조회
	int pageLimit = 3; // 한 페이지에 보여줄 게시글 수
	int blockLimit = 3; // 한 블록에 표시할 페이지 수

	public List<BoardDTO> pagingList(int page) {
		/* 1페이지당 보여지는 글 개수 설정 
		 * 1page => 0 
		 * 2page => 3 
		 * 3page => 6
		 */
		
		int pagingStart = (page - 1) * pageLimit; // 시작 인덱스 계산
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit); // 페이징 파라미터 설정
		List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams); // 페이징된 목록 반환
		return pagingList;
	}

	// 페이징 관련 파라미터 계산
	public PageDTO pagingParam(int page) {
		// 전체 게시글 수 조회
		int boardCount = boardRepository.boardCount();
		
		// 전체 페이지 수 계산 (게시글 수 / 페이지당 글 수)
		int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));

		// 현재 페이지에 맞는 시작 페이지 계산 (1, 4, 7, 10, ~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		
		// 끝 페이지 값 계산 (3, 6, 9, 12, ~~~)
		int endPage = startPage + blockLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage; // 최대 페이지 넘지 않도록 설정
		}

		// PageDTO 객체에 계산된 페이징 정보를 설정
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(page); // 현재 페이지 설정
		pageDTO.setMaxPage(maxPage); // 최대 페이지 설정
		pageDTO.setStartPage(startPage); // 시작 페이지 설정
		pageDTO.setEndPage(endPage); // 끝 페이지 설정
		
		return pageDTO; // 페이징 정보 반환
	}
}
