package com.company.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.dto.CommentDTO;
import com.company.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service // �� Ŭ������ ���� ���̾����� ��Ÿ���� ������̼�
@RequiredArgsConstructor // final �ʵ忡 ���� �����ڸ� �ڵ����� �������ִ� Lombok ������̼�
public class CommentService {
    private final CommentRepository commentRepository; // CommentRepository ������ ����

    // ��� ���� �޼ҵ�
    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO); // ����� �����ϴ� �޼ҵ� ȣ��
    }

    // �Խñۿ� ���� ��� ����� ��ȸ�ϴ� �޼ҵ�
    public List<CommentDTO> findAll(Long boardId) {
         return commentRepository.findAll(boardId); // �Խñ� ID�� ��� ����� ��ȸ
    }
}
