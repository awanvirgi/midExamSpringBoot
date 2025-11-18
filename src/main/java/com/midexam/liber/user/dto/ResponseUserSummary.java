package com.midexam.liber.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserSummary {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean deactivated;
}
