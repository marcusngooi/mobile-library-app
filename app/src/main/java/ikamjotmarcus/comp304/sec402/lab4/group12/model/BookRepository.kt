package ikamjotmarcus.comp304.sec402.lab4.group12.model

import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.BookDao
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    fun getAlphabetizedBooksByCategory(category: String): Flow<List<Book>> {
        return bookDao.getAlphabetizedBooksByCategory(category)
    }

    suspend fun getBook(bookId: Int): Book {
        return bookDao.getBook(bookId)
    }

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    suspend fun update(book: Book) {
        bookDao.update(book)
    }

    suspend fun delete(book: Book) {
        bookDao.delete(book)
    }
}