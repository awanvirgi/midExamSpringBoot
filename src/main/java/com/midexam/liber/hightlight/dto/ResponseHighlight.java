package com.midexam.liber.hightlight.dto;

import com.midexam.liber.book.dto.ResponseBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHighlight {
    private Integer orderNumber;
    private ResponseBook responseBook;
    private LocalDateTime addedDate;
}
