package com.midexam.liber.author;

import com.midexam.liber.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name",nullable = true,unique = true,length = 100)
    private String name;
    @Column(name = "Description",length = 2000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    private List<Book>books;
}
