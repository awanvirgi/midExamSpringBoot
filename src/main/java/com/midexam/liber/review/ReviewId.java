package com.midexam.liber.review;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ReviewId {
    private Integer userId;
    private Integer bookId;
}
