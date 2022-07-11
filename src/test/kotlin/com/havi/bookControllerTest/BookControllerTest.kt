package com.havi.bookControllerTest

import com.havi.controller.BookController
import com.havi.domain.Book
import com.havi.service.BookService
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(BookController::class)
class BookControllerTest {

    @MockBean private lateinit var bookService: BookService
    @Autowired private lateinit var mvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun Book_MVC_test() {
        val book = Book("Spring Boot Book", LocalDateTime.now())
        given(bookService.getBookList())
            .willReturn(Collections.singletonList(book))

        mvc.perform(get("/books"))
            .andExpect(status().isOk)
            .andExpect(view().name("book"))
            .andExpect(model().attributeExists("bookList"))
            .andExpect(model().attribute("bookList", contains(book)))
    }
}
