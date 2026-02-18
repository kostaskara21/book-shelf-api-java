package com.BookshelfApi.api.Dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BooksResponseEntity {
    List<BooksDto> context;
    private int PageNumber;
    private int PageSize;
    private int TotalPages;
    private long TotalElements;
    private boolean Last;

}
