package com.midexam.liber.user.dto;

import com.midexam.liber.shared.enums.Gender;
import com.midexam.liber.shared.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private Role role;
    private Boolean deactivated;

}
