package com.midexam.liber.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    M("Male"),
    F("Female");
    private final String description;
}
