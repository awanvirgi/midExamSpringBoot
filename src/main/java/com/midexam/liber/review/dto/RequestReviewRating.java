package com.midexam.liber.review.dto;

import com.midexam.liber.shared.enums.ReadStatus;
import com.midexam.liber.shared.validator.NotFutureTime;
import com.midexam.liber.shared.validator.NotPlannedStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReviewRating {
    @NotNull
    @NotPlannedStatus
    private ReadStatus readStatus;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotFutureTime
    @NotNull
    private LocalDateTime finishedReadingDate;
}
