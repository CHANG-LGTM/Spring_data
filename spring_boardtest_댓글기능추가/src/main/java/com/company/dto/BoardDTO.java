package com.company.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // Getter �޼��带 �ڵ����� ����
@Setter // Setter �޼��带 �ڵ����� ����
@ToString // toString() �޼��带 �ڵ����� ����
public class BoardDTO {
   private Long id; // �Խñ� ���� ID
   private String boardWriter; // �Խñ� �ۼ���
   private String boardPass; // �Խñ� ��й�ȣ
   private String boardTitle; // �Խñ� ����
   private String boardContents; // �Խñ� ����
   private int boardHits; // �Խñ� ��ȸ��
   private Timestamp boardCreatedTime; // �Խñ� ���� �ð�
}
