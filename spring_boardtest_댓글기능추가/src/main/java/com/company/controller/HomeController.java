package com.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j // Log4j ������̼��� ����Ͽ� �α� ����
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // SLF4J �ΰ� �ʱ�ȭ
	
	@RequestMapping({"","/"}) // ��Ʈ ���(�� ��ο� "/")�� ���� ��û�� ó��
	public String home() {
		log.info("Welcome home!"); // �α�: "Welcome home!" �޽��� ���
		return "index"; // "index" �� �������� �̵�
	}
}
