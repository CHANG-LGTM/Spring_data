package com.company.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter �޼ҵ� �ڵ� ����
@Setter // setter �޼ҵ� �ڵ� ����
@ToString // toString �޼ҵ� �ڵ� ����
public class CommentDTO {
    private Long id; // ��� ID
    private String commentWriter; // ��� �ۼ���
    private String commentContent; // ��� ����
    private Long boardId; // ����� �޸� �Խñ��� ID
    private Timestamp commentCreatedTime; // ��� �ۼ� �ð�
}
