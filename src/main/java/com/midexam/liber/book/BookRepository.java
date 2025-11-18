package com.midexam.liber.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Boolean existsByAuthorId(Integer id);


    @Query("""
           SELECT b FROM Book b where (:name is null or lower(b.name) like %:name%) and b.deleted is false
           """)
    Page<Book> findAll(Pageable pageable,String name);

    Optional<Book> findByIdAndDeletedFalse(Integer id);
    Boolean existsByIdAndDeletedFalse(Integer id);
}
