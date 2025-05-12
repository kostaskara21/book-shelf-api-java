package com.BookshelfApi.api.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksDto {
    private String Title;
    private String Author;
    private  String Description;
}
