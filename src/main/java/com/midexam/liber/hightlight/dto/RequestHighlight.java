package com.midexam.liber.hightlight.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHighlight {
    @NotNull
    private Integer orderNumber;
    @NotNull
    private Integer bookId;
}
