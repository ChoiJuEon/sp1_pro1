package com.example.hello_spring.service;

import com.example.hello_spring.domain.Board;
import com.example.hello_spring.repository.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService { //Service Layer
    @Autowired
    private BoardRepo boardRepo;

    public Board createBoard(Board board){ //Create Board Table with Object Board
        return boardRepo.save(board);
    }

    public void deleteBoard(Long id){ //Delete Data from Board Table with ID
        boardRepo.deleteById(id);
    }
}