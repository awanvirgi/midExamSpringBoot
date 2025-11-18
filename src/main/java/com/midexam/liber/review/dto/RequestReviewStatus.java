package com.midexam.liber.review.dto;

import com.midexam.liber.shared.enums.ReadStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReviewStatus {
    @NotNull
    private ReadStatus readStatus;
}
