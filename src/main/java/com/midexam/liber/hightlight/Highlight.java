package com.midexam.liber.hightlight;

import com.midexam.liber.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Highlights")
public class Highlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "OrderNumber",unique = true,nullable = false)
    private Integer orderNumber;
    @Column(name = "AddedDate")
    private LocalDateTime addedDate;
    @OneToOne
    @JoinColumn(name = "Book_id",referencedColumnName = "id",unique = true)
    private Book book;

    @PrePersist
    private void onCreate(){
        if (this.addedDate == null){
            this.addedDate = LocalDateTime.now();
        }
    }
}
