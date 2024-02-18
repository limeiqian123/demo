package com.example.demo.eo;

import lombok.Data;

@Data
public class BookEo {

    private Integer id;

    private String isbn;

    private String bookName;

    private String author;

    private Integer publishTime;
}
