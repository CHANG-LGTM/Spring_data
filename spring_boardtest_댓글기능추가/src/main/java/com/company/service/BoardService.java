package com.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.dto.BoardDTO;
import com.company.dto.PageDTO;
import com.company.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service // �� Ŭ������ ���� ������ ó���ϴ� ������Ʈ
@RequiredArgsConstructor // final �ʵ忡 ���� ������ �ڵ� ����
public class BoardService {
	
	private final BoardRepository boardRepository; // BoardRepository ����

	// �Խñ� ����
	public int save(BoardDTO boardDTO) {
		return boardRepository.save(boardDTO); // �Խñ� ���� �� ��� ��ȯ
	}

	// ��� �Խñ� ��ȸ
	public List<BoardDTO> findAll() {
		return boardRepository.findAll(); // ��� �Խñ� ��ȸ
	}

	// �Խñ� ID�� ��ȸ
	public BoardDTO findById(Long id) {
		return boardRepository.findById(id); // ID�� �Խñ� ��ȸ
	}

	// �Խñ� ��ȸ�� ����
	public void updateHits(Long id) {
		boardRepository.updateHits(id); // ��ȸ�� ����
	}

	// �Խñ� ����
	public void delete(Long id) {
		boardRepository.delete(id); // �Խñ� ����
	}

	// �Խñ� ����
	public void update(BoardDTO boardDTO) {
		boardRepository.update(boardDTO); // �Խñ� ����
	}

	// ����¡�� �Խñ� ��� ��ȸ
	int pageLimit = 3; // �� �������� ������ �Խñ� ��
	int blockLimit = 3; // �� ��Ͽ� ǥ���� ������ ��

	public List<BoardDTO> pagingList(int page) {
		/* 1�������� �������� �� ���� ���� 
		 * 1page => 0 
		 * 2page => 3 
		 * 3page => 6
		 */
		
		int pagingStart = (page - 1) * pageLimit; // ���� �ε��� ���
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit); // ����¡ �Ķ���� ����
		List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams); // ����¡�� ��� ��ȯ
		return pagingList;
	}

	// ����¡ ���� �Ķ���� ���
	public PageDTO pagingParam(int page) {
		// ��ü �Խñ� �� ��ȸ
		int boardCount = boardRepository.boardCount();
		
		// ��ü ������ �� ��� (�Խñ� �� / �������� �� ��)
		int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));

		// ���� �������� �´� ���� ������ ��� (1, 4, 7, 10, ~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		
		// �� ������ �� ��� (3, 6, 9, 12, ~~~)
		int endPage = startPage + blockLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage; // �ִ� ������ ���� �ʵ��� ����
		}

		// PageDTO ��ü�� ���� ����¡ ������ ����
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(page); // ���� ������ ����
		pageDTO.setMaxPage(maxPage); // �ִ� ������ ����
		pageDTO.setStartPage(startPage); // ���� ������ ����
		pageDTO.setEndPage(endPage); // �� ������ ����
		
		return pageDTO; // ����¡ ���� ��ȯ
	}
}
