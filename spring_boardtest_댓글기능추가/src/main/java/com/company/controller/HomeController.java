package com.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j // Log4j 어노테이션을 사용하여 로깅 설정
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // SLF4J 로거 초기화
	
	@RequestMapping({"","/"}) // 루트 경로(빈 경로와 "/")에 대한 요청을 처리
	public String home() {
		log.info("Welcome home!"); // 로깅: "Welcome home!" 메시지 출력
		return "index"; // "index" 뷰 페이지로 이동
	}
}
