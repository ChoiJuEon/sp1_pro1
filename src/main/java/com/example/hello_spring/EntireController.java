package com.example.hello_spring;

import com.example.hello_spring.domain.Board;
import com.example.hello_spring.repository.BoardRepo;
import com.example.hello_spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EntireController { //Main Controller
    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private BoardService boardService;

    @GetMapping("create") //Mapping API GET
    public String create(Model model){ //Create Function
        model.addAttribute("board", new Board()); //Deliver Data
        return "create"; //create.html
    }

    @GetMapping //Mapping API GET
    public String page(Model model, @RequestParam(required = false) Integer page) { //Pagination Function
        List<Board> boardList = new ArrayList<>();
        PageRequest pageRequest;
        if (page == null) {
            pageRequest = PageRequest.of(0, 8);
        } else {
            pageRequest = PageRequest.of(page - 1, 8);
        }

        boardList = boardRepo.findAll(pageRequest).getContent();
        model.addAttribute("boardList", boardList);

        long count = boardRepo.count();
        long pageCount = 1;
        if (count > 8) { //8 Different Data in Each Page
            pageCount = count / 8;
            if ((count % 8) >= 1) {
                pageCount = pageCount + 1;
            }
        }
        model.addAttribute("page", pageCount); //Deliver Data
        return "index"; //index.html
    }

    @GetMapping(path = "viewer/{id}") //Mapping API GET
    public String viewer(Model model, @PathVariable Long id){ //Read Function
        Optional<Board> board = boardRepo.findById(id); //Find Data with ID
        Board boardEntity = board.get(); //Get
        boardEntity.setViewCount(boardEntity.getViewCount() + 1); //View Count Increase

        Board boardAfterSave = boardRepo.save(boardEntity);
        model.addAttribute("board", boardAfterSave); //Deliver Data
        return "viewer"; //viewer.html
    }

    @GetMapping(path = "viewer/update/{id}") //Mapping API GET
    public String viewerUpdate(Model model, @PathVariable Long id) { //Modify Function
        Optional<Board> board = boardRepo.findById(id); //Find Data with ID
        model.addAttribute("board", board.get()); //Deliver Data
        return "update"; //update.html
    }

    @PostMapping(path = "viewer/{id}") //Mapping API POST
    public String remove(@PathVariable("id") Long id){ //Remove Function
        boardService.deleteBoard(id); //Delete Data with ID
        return "redirect:/"; //index.html
    }
}