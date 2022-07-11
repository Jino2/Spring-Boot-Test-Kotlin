package com.havi.bookJsonTest

import com.havi.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester

@JsonTest
class BookJsonTest {
    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    @Throws(Exception::class)
    fun jsonTest() {
        val book = Book(
            title = "test",
            publishedAt = null
        )
        val content = "{\"title\":\"test\"}"

        assertThat(json.parseObject(content).title).isEqualTo(book.title)
        assertThat(json.parseObject(content).publishedAt).isNull()

        assertThat(json.write(book)).isEqualToJson("/test.json")
        assertThat(json.write(book)).hasJsonPathStringValue("title")
        assertThat(json.write(book)).extractingJsonPathStringValue("title").isEqualTo("test")

    }
}
