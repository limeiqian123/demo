package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.Response;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    BookService bookService;

    @PostMapping("/add")
    @ResponseBody
    public Response add(@RequestBody BookDto bookDto) {
        Response response = new Response();
        Optional<BookDto> result = bookService.add(bookDto);
        response.setData(result.get());
        return response;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Response query(@PathVariable("id") Integer id) {
        Response response = new Response();
        Optional<BookDto> result = bookService.query(id);
        response.setData(result.get());
        return response;
    }

    @GetMapping("/list")
    @ResponseBody
    public Response list() {
        Response response = new Response();
        Optional<List<BookDto>> result = bookService.list();
        response.setData(result.get());
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Response delete(@PathVariable("id") Integer id) {
        Response response = new Response();
        Integer count = bookService.delete(id);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("count", count);
        response.setData(map);
        return response;
    }

    @PutMapping("/update")
    @ResponseBody
    public Response update(@RequestBody BookDto bookDto) {
        Response response = new Response();
        Optional<BookDto> update = bookService.update(bookDto);
        response.setData(update.get());
        return response;
    }


}
