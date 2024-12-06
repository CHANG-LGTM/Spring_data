package com.company.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.company.dto.BoardDTO;

import lombok.RequiredArgsConstructor;

@Service // �� Ŭ������ ���� ������ �Ϻη� ó���Ǵ� ������Ʈ
@RequiredArgsConstructor // final �ʵ忡 ���� �����ڸ� �ڵ� ����
public class BoardRepository {

	private final SqlSessionTemplate sql; // MyBatis SQL ���� ���ø� ����
	
	// �Խñ� ����
	public int save(BoardDTO boardDTO) {
		return sql.insert("Board.save", boardDTO); // SQL ���� ����
	}

	// ��� �Խñ� ��� ��ȸ
	public List<BoardDTO> findAll() {
		return sql.selectList("Board.findAll"); // SQL ���� ����
	}

	// �Խñ� ID�� ��ȸ
	public BoardDTO findById(Long id) {
		return sql.selectOne("Board.findById", id); // SQL ���� ����
	}

	// �Խñ� ��ȸ�� ����
	public void updateHits(Long id) {
		sql.update("Board.updateHits", id); // SQL ���� ����
	}

	// �Խñ� ����
	public void delete(Long id) {
		sql.delete("Board.delete", id); // SQL ���� ����
	}

	// �Խñ� ����
	public void update(BoardDTO boardDTO) {
		sql.update("Board.update", boardDTO); // SQL ���� ����
	}

	// ����¡�� ���� �Խñ� ��� ��ȸ
	public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
		return sql.selectList("Board.pagingList", pagingParams); // SQL ���� ����
	}

	// ��ü �Խñ� �� ��ȸ
	public int boardCount() {
		return sql.selectOne("Board.boardCount"); // SQL ���� ����
	}
}
