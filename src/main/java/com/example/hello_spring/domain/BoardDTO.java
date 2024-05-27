package com.example.hello_spring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class BoardDTO { //Board
    private String id;
    private String title;
    private String contents;
    private Long viewCount;
    private LocalDate createdTime;
}