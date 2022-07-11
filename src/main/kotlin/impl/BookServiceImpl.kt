package impl

import com.havi.domain.Book
import com.havi.service.BookService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class BookServiceImpl: BookService {

    override fun getBookList(): List<Book> {
        return emptyList()
    }
}
