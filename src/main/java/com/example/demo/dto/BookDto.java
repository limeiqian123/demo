package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDto {

    private Integer id;

    private String isbn;

    private String bookName;

    private String author;

    private String publishDate;

}
