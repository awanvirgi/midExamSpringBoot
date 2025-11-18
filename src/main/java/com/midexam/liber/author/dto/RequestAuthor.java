package com.midexam.liber.author.dto;

import com.midexam.liber.shared.validator.UniqueAuthorName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuthor {
    @NotNull
    @NotBlank
    @Size(max = 100)
    @UniqueAuthorName
    private String name;
    @Size(max = 2000)
    private String description;
}
