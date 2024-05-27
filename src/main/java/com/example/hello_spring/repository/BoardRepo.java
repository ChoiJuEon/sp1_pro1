package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepo extends JpaRepository<Board, Long> { //Interface which Access Crated DB

}