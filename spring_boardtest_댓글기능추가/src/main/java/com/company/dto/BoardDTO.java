package com.company.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // Getter 메서드를 자동으로 생성
@Setter // Setter 메서드를 자동으로 생성
@ToString // toString() 메서드를 자동으로 생성
public class BoardDTO {
   private Long id; // 게시글 고유 ID
   private String boardWriter; // 게시글 작성자
   private String boardPass; // 게시글 비밀번호
   private String boardTitle; // 게시글 제목
   private String boardContents; // 게시글 내용
   private int boardHits; // 게시글 조회수
   private Timestamp boardCreatedTime; // 게시글 생성 시간
}
