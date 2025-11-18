package com.midexam.liber.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Page<Author> findAll(Pageable pageable);
    Boolean existsByName(String name);

    @Query("""
           SELECT CASE WHEN EXISTS (
           SELECT a FROM Author a where a.name = :name and a.id != :id
           ) Then true else false end
           """)
    Boolean existsByName(String name,Integer id);
}
