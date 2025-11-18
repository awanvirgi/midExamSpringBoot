package com.midexam.liber.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBook {
    private Integer id;
    private String name;
    private String cover;
    private LocalDate releaseDate;
    private String authorName;
}
