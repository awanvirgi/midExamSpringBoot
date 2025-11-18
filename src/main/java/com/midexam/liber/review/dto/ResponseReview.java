package com.midexam.liber.review.dto;

import com.midexam.liber.book.dto.ResponseBook;
import com.midexam.liber.shared.enums.ReadStatus;
import com.midexam.liber.user.dto.ResponseUserSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReview {
    private String title;
    private String description;
    private LocalDate addedDate;
    private ReadStatus readStatus;
    private LocalDateTime finishedReadingDate;
    private LocalDateTime reviewDate;
    private Integer rating; // iini dicase gk ad
    private ResponseUserSummary user;
    private ResponseBook book;
}
