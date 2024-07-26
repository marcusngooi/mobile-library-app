package ikamjotmarcus.comp304.sec402.lab4.group12.model.dao

import androidx.room.*
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Query("SELECT password FROM student_table WHERE student_id = :studentId")
    suspend fun getStudentPassword(studentId: String) : String

    @Query("SELECT * FROM student_table WHERE student_id = :studentId")
    suspend fun getStudent(studentId: String) : Student

    @Query("SELECT * from student_table WHERE student_id = :studentId")
    suspend fun getStudentByNameAndStudentId(studentId: String): Student?

    @Query ("UPDATE student_table SET book_id = :newBookId WHERE student_id = :studentId")
    suspend fun updateStudentBookId(studentId: String, newBookId: Int)

}