package com.example.hello_spring.domain;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*; // spring 3.x.x 버전 이상 javax가 아닌 jakarta로 변경
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
public class Board { //Board Class
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table Column Increase Id(Key Value)
    private Long id;
    @Column //Table Column named String
    private String title;
    @Column //Table Column named contents
    private String contents;
    @Column //Table Column named createdTime
    @CreatedDate
    private LocalDate createdTime;
    @Column //Table Column named viewCount
    private Long viewCount;
}