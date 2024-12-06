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

@Controller // �� Ŭ������ Spring MVC�� ��Ʈ�ѷ� ������ �Ѵٴ� ���� ���
@RequiredArgsConstructor // Lombok ������̼�����, final �ʵ忡 ���� �����ڸ� �ڵ� ����
@RequestMapping("/comment") // "/comment" ��η� ������ ��û�� ó���ϴ� ��Ʈ�ѷ�
public class CommentController {

    private final CommentService commentService; // ��� ���� ������ ����

    // ��� ���� �� �ش� �Խñۿ� ���� ��� ��� ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
    @PostMapping("/save") // "/save" ��η� POST ��û�� ó��
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO); // �α׷� ���޵� ��� ������ ���
        commentService.save(commentDTO); // ��� ����
        // �ش� �Խñۿ� �ۼ��� ��� ����Ʈ�� �����ͼ� ��ȯ
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return commentDTOList; // ��� ����� JSON �������� ��ȯ
    }
}
