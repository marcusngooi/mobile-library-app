package ikamjotmarcus.comp304.sec402.lab4.group12.model.dao

import androidx.room.*
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books_table WHERE category = :category ORDER BY book_name ASC ")
    fun getAlphabetizedBooksByCategory(category: String): Flow<List<Book>>

    @Query("SELECT * FROM books_table WHERE book_id = :bookId")
    suspend fun getBook(bookId: Int): Book

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book:Book)
}