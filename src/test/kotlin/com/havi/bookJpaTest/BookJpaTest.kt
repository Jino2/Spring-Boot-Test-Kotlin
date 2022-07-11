package com.havi.bookJpaTest

import com.havi.domain.Book
import com.havi.repository.BookRepository
import net.bytebuddy.asm.Advice.Local
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.collection.IsEmptyCollection
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class BookJpaTest {
    companion object {
        const val BOOT_TEST_TITLE = "Spring Boot Test Book"
    }

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun bookSaveTest() {
        val book = Book(
            title = BOOT_TEST_TITLE,
            publishedAt = LocalDateTime.now()
        )

        testEntityManager.persist(book)
        assertEquals(bookRepository.findById(book.idx).get(), book)
    }

    @Test
    fun bookListSaveAndSearchTest() {
        val book1 = Book(
            title = "${BOOT_TEST_TITLE}1",
            publishedAt = LocalDateTime.now()
        )
        val book2 = Book(
            title = "${BOOT_TEST_TITLE}2",
            publishedAt = LocalDateTime.now()
        )
        val book3 = Book(
            title = "${BOOT_TEST_TITLE}3",
            publishedAt = LocalDateTime.now()
        )

        testEntityManager.persist(book1)
        testEntityManager.persist(book2)
        testEntityManager.persist(book3)

        val bookList = bookRepository.findAll()
        assertEquals(bookList.size, 3)
        assertThat(bookList, contains(book1, book2, book3))
    }

    @Test
    fun bookListSaveAndDeleteTest() {
        val book1 = Book(
            title = "${BOOT_TEST_TITLE}1",
            publishedAt = LocalDateTime.now()
        )
        val book2 = Book(
            title = "${BOOT_TEST_TITLE}2",
            publishedAt = LocalDateTime.now()
        )
        testEntityManager.persist(book1)
        testEntityManager.persist(book2)

        bookRepository.deleteAll()
        assertThat(bookRepository.findAll(), IsEmptyCollection.empty())
    }
}
