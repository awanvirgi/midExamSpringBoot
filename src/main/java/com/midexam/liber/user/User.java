package com.midexam.liber.user;

import com.midexam.liber.review.Review;
import com.midexam.liber.shared.enums.Gender;
import com.midexam.liber.shared.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column( name = "Username",unique = true)
    private String userName;
    @Column( name = "Email")
    private String email;
    @Column( name = "FirstName")
    private String firstName;
    @Column( name = "LastName")
    private String lastName;
    @Column( name = "BirthDate")
    private LocalDate birthDate;
    @Enumerated(value = EnumType.STRING)
    @Column( name = "Gender")
    private Gender gender;
    @Column( name = "Role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column( name = "Deactivated")
    private Boolean deactivated;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Review> reviews;
}
