package com.company.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter 메소드 자동 생성
@Setter // setter 메소드 자동 생성
@ToString // toString 메소드 자동 생성
public class CommentDTO {
    private Long id; // 댓글 ID
    private String commentWriter; // 댓글 작성자
    private String commentContent; // 댓글 내용
    private Long boardId; // 댓글이 달린 게시글의 ID
    private Timestamp commentCreatedTime; // 댓글 작성 시간
}
