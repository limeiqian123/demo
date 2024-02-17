package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.eo.BookEo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testBookAdd() {
        BookEo bookEo = new BookEo();
        bookEo.setIsbn("9787011234561");
        bookEo.setBookName("A Little Prince");
        bookEo.setAuthor("Joke");
        bookEo.setPublishDate("1993-01-23");

        bookMapper.saveOne(bookEo);

        assertNotNull(bookEo.getId());
        assertEquals("A Little Prince", bookEo.getBookName());

    }

    @Test
    public void testQueryById() {

        BookEo bookEo = bookMapper.queryById(3);

        assertEquals("A Little Prince", bookEo.getBookName());

    }

    @Test
    public void testListAll() {

        List<BookEo> bookEos = bookMapper.listAll();

        assertEquals("A Little Prince", bookEos.get(2).getBookName());
    }

    @Test
    public void testDelete() {

        BookEo bookEo = new BookEo();
        bookEo.setIsbn("9787011234662");
        bookEo.setBookName("A Prince");
        bookEo.setAuthor("Joker");
        bookEo.setPublishDate("1993-03-23");

        bookMapper.saveOne(bookEo);

        Integer id = bookEo.getId();
        Integer count = bookMapper.delete(id);
        assertEquals(1, count);
    }
    
    @Test
    public void testUpdate(){

        BookEo bookEo = new BookEo();
        bookEo.setId(1);
        bookEo.setIsbn("9787011234662");
        bookEo.setBookName("Bleak House");
        bookEo.setAuthor("Toms");
        bookEo.setPublishDate("1924-01-01");

        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(bookEo,bookDto);

        Integer count = bookMapper.update(bookDto);

        assertTrue(count > 0);

    }

}
