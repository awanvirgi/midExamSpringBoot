package com.midexam.liber.book;

import com.midexam.liber.author.Author;
import com.midexam.liber.hightlight.Highlight;
import jakarta.persistence.*;
        import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name",nullable = false,length = 100)
    private String name;
    @Column(name = "Cover",nullable = false,length = 255)
    private String cover;
    @Column(name = "ReleaseDate",nullable = false)
    private LocalDate releaseDate;
    @Column(name = "IsDeleted")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;

    @PrePersist
    private void onCreate(){
        if (this.deleted == null){
            this.deleted = false;
        }
    }
}
