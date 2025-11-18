package com.midexam.liber.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private PaginationResponse pagination;
    private List<T> content;

    public PageResponse(Page<T> page){
        PaginationResponse response = new PaginationResponse();
        response.setPage(page.getTotalPages());
        response.setSize(page.getSize());
        response.setSort(page.getSort().toString());
        this.pagination = response;
        this.content = page.getContent();
    }
}
