package com.company.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.dto.CommentDTO;

import lombok.RequiredArgsConstructor;

@Repository // �� Ŭ������ Spring�� Repository ������ �� (�����ͺ��̽����� ��ȣ�ۿ� ���)
@RequiredArgsConstructor // Lombok ������̼�����, final �ʵ忡 ���� �����ڸ� �ڵ����� ����
public class CommentRepository {
    
    private final SqlSessionTemplate sql; // MyBatis�� SqlSessionTemplate�� ����Ͽ� DB ����

    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save", commentDTO); // "Comment.save" ���� ID�� ����Ͽ� ����� DB�� ����
    }

    public List<CommentDTO> findAll(Long boardId) {
        return sql.selectList("Comment.findAll", boardId); // "Comment.findAll" ���� ID�� ����Ͽ� Ư�� �Խñۿ� ���� ��� ����� ��ȸ
    }
}
