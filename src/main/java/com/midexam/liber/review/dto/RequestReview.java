package com.midexam.liber.review.dto;

import com.midexam.liber.shared.enums.ReadStatus;
import com.midexam.liber.shared.validator.NotFutureTime;
import com.midexam.liber.shared.validator.NotPlannedStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReview {
    @NotNull
    @Size(min = 5,max = 25)
    private String title;
    @NotNull
    @Size(min = 255,max = 8000)
    private String description;
    @NotPlannedStatus
    @NotNull
    private ReadStatus readStatus;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    @NotFutureTime
    private LocalDateTime finishedReadingDate;
}
