package ikamjotmarcus.comp304.sec402.lab4.group12.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Librarian

@Dao
interface LibrarianDao {
    @Query("SELECT password FROM librarian_table WHERE librarian_id = :librarianId")
    suspend fun getLibrarianPassword(librarianId: String): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(librarian: Librarian)
}