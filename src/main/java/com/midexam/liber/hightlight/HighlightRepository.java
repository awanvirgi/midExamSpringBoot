package com.midexam.liber.hightlight;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HighlightRepository extends JpaRepository<Highlight,Integer> {
    Page<Highlight> findAll(Pageable pageable);
    Optional<Highlight> findByOrderNumber(Integer id);
    @Modifying
    @Transactional
    void deleteByOrderNumber(Integer id);
    Boolean existsByOrderNumber(Integer id);
    Boolean existsByBookId(Integer bookId);
}
