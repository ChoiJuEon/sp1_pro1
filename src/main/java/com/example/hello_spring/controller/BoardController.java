package com.example.hello_spring.controller;

import com.example.hello_spring.domain.Board;
import com.example.hello_spring.domain.BoardDTO;
import com.example.hello_spring.repository.BoardRepo;
import com.example.hello_spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class BoardController { //Board DB Table Controller
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepo boardRepo;

    @PostMapping("board") //Mapping API POST
    public RedirectView createBoard(@ModelAttribute("board") Board board){ //Create Board DB Table
        board.setViewCount(0L);
        boardService.createBoard(board);
        return new RedirectView("/");
    }

    @PutMapping("board/{id}") //Mapping API PUT
    public RedirectView updateBoard(@PathVariable Long id,@ModelAttribute("board") BoardDTO boardDTO){ //Modify Board DB Table
        try {
            Optional<Board> updateBoard = boardRepo.findById(id); //Find Data with ID
            Board board1 = updateBoard.get(); //Get
            board1.setContents(boardDTO.getContents()); //Set Board contents with Modify one
            board1.setTitle(boardDTO.getTitle()); //Set Board title with Modify one
            boardRepo.save(board1); //Save Board
        } catch (Exception e){ //Exception Handling
            e.printStackTrace();
        }
        return new RedirectView("/");
    }

}