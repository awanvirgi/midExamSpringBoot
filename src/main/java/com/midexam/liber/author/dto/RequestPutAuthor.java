package com.midexam.liber.author.dto;

import com.midexam.liber.shared.validator.UniqueAuthorName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPutAuthor {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 2000)
    private String description;
}
