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
import com.company.dto.CommentDTO;
import com.company.dto.PageDTO;
import com.company.service.BoardService;
import com.company.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller // �� Ŭ������ Spring MVC�� ��Ʈ�ѷ� ������ �Ѵٴ� ���� ���
@RequiredArgsConstructor // Lombok ������̼�����, final �ʵ忡 ���� �����ڸ� �ڵ����� ����
@RequestMapping("/board") // "/board" ��ο� ���� ��û�� ó���ϴ� ��Ʈ�ѷ�
public class BoardController {

    private final BoardService boardService; // BoardService ������ ����
    private final CommentService commentService; // CommentService ������ ����

    @GetMapping("/save") // �� �ۼ� �� �������� �����ִ� ��û
    public String saveForm() {
        return "save"; // �� �ۼ� �� ��� �̵�
    }

    @PostMapping("/save") // �� �ۼ� ������ ����� �����͸� ó��
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO); // �� ���� ó��
        return "redirect:/board/paging"; // ���� �� ����¡�� �� ������� �����̷�Ʈ
    }

    @GetMapping("/") // ��� �Խñ� ����� �������� ��û
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll(); // ��� �Խñ� ����Ʈ ��ȸ
        model.addAttribute("boardList", boardDTOList); // ��ȸ�� �Խñ� ����Ʈ�� �𵨿� �߰�
        return "list"; // �Խñ� ��� ��� �̵�
    }

    @GetMapping // �Խñ� �� ���� ��û
    public String findById(@RequestParam("id") Long id, 
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
                           Model model) {
        boardService.updateHits(id); // �Խñ� ��ȸ�� ����
        BoardDTO boardDTO = boardService.findById(id); // �Խñ� �� ���� ��ȸ
        model.addAttribute("board", boardDTO); // �Խñ� ���� �𵨿� �߰�
        model.addAttribute("page", page); // ������ ��ȣ �𵨿� �߰�
        List<CommentDTO> commentDTOList = commentService.findAll(id); // �ش� �Խñۿ� �ۼ��� ��� ����Ʈ ��ȸ
        model.addAttribute("commentList", commentDTOList); // ��� ����Ʈ �𵨿� �߰�
        return "detail"; // �Խñ� �� �������� �̵�
    }

    @GetMapping("/delete") // �Խñ� ���� ��û
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id); // �ش� �Խñ� ���� ó��
        return "redirect:/board/"; // ���� �� �Խñ� ������� �����̷�Ʈ
    }

    @GetMapping("/update") // �Խñ� ���� �� ��û
    public String updateForm(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id); // ������ �Խñ� ��ȸ
        model.addAttribute("board", boardDTO); // ������ �Խñ� ���� �𵨿� �߰�
        return "update"; // �Խñ� ���� �� �������� �̵�
    }

    @PostMapping("/update") // ������ �Խñ� �����͸� ó���ϴ� ��û
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO); // �Խñ� ���� ó��
        BoardDTO dto = boardService.findById(boardDTO.getId()); // ������ �Խñ� ���� ��ȸ
        model.addAttribute("board", dto); // ������ �Խñ� ������ �𵨿� �߰�
        return "detail"; // ������ �Խñ��� �� �������� �̵�
    }

    @GetMapping("/paging") // �Խñ� ����¡ ó���� ���� ��û
    public String paging(Model model, 
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        List<BoardDTO> pagingList = boardService.pagingList(page); // ������ ��ȣ�� �´� �Խñ� ��� ��ȸ
        PageDTO pageDTO = boardService.pagingParam(page); // ������ ���� ���� ��ȸ
        model.addAttribute("boardList", pagingList); // ����¡�� �Խñ� ����Ʈ �𵨿� �߰�
        model.addAttribute("paging", pageDTO); // ����¡ ���� �𵨿� �߰�
        return "paging"; // ����¡�� �Խñ� ��� �������� �̵�
    }
}
