package com.BookshelfApi.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookEndpointsConstants {
    public static final String GET_ALL_BOOKS = "/books";
    public static final String GET_BOOK = "Books/{id}";
    public static final String CREATE_BOOK = "Books/Create";
    public static final String UPDATE_BOOK = "Books/{id}/Update";
    public static final String DELETE_BOOK = "Books/{id}/Delete";

}
