package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.eo.BookEo;
import com.example.demo.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void testBookAdd() {
        BookEo bookReturn = new BookEo();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        Mockito.when(bookMapper.saveOne(Mockito.any(BookEo.class))).thenReturn(1);

        BookDto bookInput = new BookDto();
        bookInput.setIsbn("9787011334567");
        bookInput.setBookName("Mr. Bean");
        bookInput.setAuthor("Mr. White");
        bookInput.setPublishTime(1922);

        Optional<BookDto> result = bookService.add(bookInput);

        assertEquals(result.get().getIsbn(),bookInput.getIsbn());
    }

    @Test
    public void testBookQuery() {
        BookEo bookReturn = new BookEo();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        Mockito.when(bookMapper.queryById(Mockito.anyInt())).thenReturn(bookReturn);

        Optional<BookDto> result = bookService.query(1);
        assertEquals("Mr. Bean", result.get().getBookName());
    }

    @Test
    public void testBookListAll() {
        BookEo bookReturn = new BookEo();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        List<BookEo> bookEoList = new ArrayList<>();
        bookEoList.add(bookReturn);

        Mockito.when(bookMapper.listAll()).thenReturn(bookEoList);

        Optional<List<BookDto>> result = bookService.list();
        assertEquals(Boolean.TRUE.booleanValue(), result.get().size() > 0);
    }

    @Test
    public void testDelete() {
        Mockito.when(bookMapper.delete(Mockito.anyInt())).thenReturn(1);
        assertEquals(1,bookService.delete(1));
    }

    @Test
    public void testUpdate(){
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setBookName("Mrs. J");
        bookDto.setAuthor("Mrs. B");

        BookEo bookReturn = new BookEo();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);


        Mockito.when(bookMapper.update(bookDto)).thenReturn(1);
        Mockito.when(bookMapper.queryById(1)).thenReturn(bookReturn);

        Optional<BookDto> update = bookService.update(bookDto);

        BookDto updatedBookDto = update.get();
        assertEquals(updatedBookDto.getId(),1);
        assertEquals(updatedBookDto.getBookName(), "Mrs. J");
        assertEquals(updatedBookDto.getAuthor(),"Mrs. B");
        assertEquals(updatedBookDto.getPublishTime(), 1922);
    }

}
