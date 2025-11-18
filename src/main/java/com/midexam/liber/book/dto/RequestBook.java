package com.midexam.liber.book.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBook {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Size(max = 255)
    private String cover;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private Integer authorId;
}
