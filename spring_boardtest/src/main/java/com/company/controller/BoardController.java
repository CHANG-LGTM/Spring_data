package com.company.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dto.BoardDTO;
import com.company.dto.PageDTO;
import com.company.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // /board ��ο� ���� ��û�� ó���ϴ� ��Ʈ�ѷ�
public class BoardController {
	
	private final BoardService boardService; // BoardService ����

	@GetMapping("/save")
	public String saveForm() {
		// �� �ۼ� �� �������� �̵�
		return "save";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) {
		// �� ���� ó�� ��, �� ��� �������� �����̷�Ʈ
		int saveResult = boardService.save(boardDTO);
		return "redirect:/board/paging"; // ���� �� ����¡�� ������� �����̷�Ʈ
	}

	@GetMapping("/")
	public String findAll(Model model) {
		// ��� �Խñ� ����� �����ͼ� �𵨿� �߰��ϰ� ����Ʈ �������� �̵�
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "list";
	}	

	@GetMapping
	public String findById(@RequestParam("id") Long id,
	                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	                       Model model) {
		// �Խñ� ��ȸ �� ��ȸ���� ������Ű��, �� �������� �̵�
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", page); // ������ ��ȣ�� �Բ� ����
		return "detail"; // �� ���� �������� �̵�
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		// �ش� �Խñ��� ������ �� �Խñ� ������� �����̷�Ʈ
		boardService.delete(id);
		return "redirect:/board/"; // ���� �� �� ������� �����̷�Ʈ
	}

	@GetMapping("/update")
	public String updateForm(@RequestParam("id") Long id, Model model) {
		// ������ �Խñ��� ������ �����ͼ� ���� ������ ����
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "update"; // ���� �� �������� �̵�
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		// �Խñ� ���� ó�� ��, ������ ���� �� �������� �̵�
		boardService.update(boardDTO);
		BoardDTO dto = boardService.findById(boardDTO.getId());
		model.addAttribute("board", dto);
		return "detail"; // ������ �Խñ��� �� �������� �̵�
	}

	// /board/paging?page=2
    // ó�� ������ ��û�� 1�������� ������
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        // ������ ��ȣ�� �´� �Խñ� ����� �����ͼ� ����¡ ó��
        List<BoardDTO> pagingList = boardService.pagingList(page);
        PageDTO pageDTO = boardService.pagingParam(page); // ������ ���� ���� ��������
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO); // ����¡ ������ �𵨿� �߰�
        return "paging"; // ����¡�� ����� �����ִ� �������� �̵�
    }  
}
