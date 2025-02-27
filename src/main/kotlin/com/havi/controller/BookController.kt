package com.havi.controller

import com.havi.service.BookService
import impl.BookServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/books")
class BookController(
    private val bookService: BookService,
) {
    @GetMapping
    fun getBookList(model: Model): String {
        model.addAttribute("bookList", bookService.getBookList())
        return "book"
    }

    @Bean
    fun bind(): BookService {
        return BookServiceImpl()
    }
}
