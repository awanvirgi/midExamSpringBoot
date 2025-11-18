package com.midexam.liber.review;

import com.midexam.liber.book.Book;
import com.midexam.liber.shared.enums.ReadStatus;
import com.midexam.liber.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Reviews")
public class Review {
    @EmbeddedId
    private ReviewId reviewId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description",length = 8000)
    private String description;
    @Column(name = "AddedDate")
    private LocalDate addedDate;
    @Column(name = "ReadStatus",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReadStatus readStatus;
    @Column(name = "FinishingReadingDate")
    private LocalDateTime finishedReadingDate;
    @Column(name = "ReviewDate")
    private LocalDateTime reviewDate;
    @Column(name = "Rating")
    private Integer rating;
    @Column(name = "Deactivated")
    private Boolean deactivated;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",nullable = false)
    private User user;
    @MapsId("bookId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookId",nullable = false)
    private Book book;

    @PrePersist
    private void onCreate(){
        if (this.addedDate == null) {
            this.addedDate = LocalDate.now();
        }
        if (this.deactivated == null){
            this.deactivated = false;
        }
    }
}
