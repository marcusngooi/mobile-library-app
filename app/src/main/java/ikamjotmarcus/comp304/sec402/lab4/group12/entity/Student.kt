package ikamjotmarcus.comp304.sec402.lab4.group12.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student(
    @PrimaryKey @ColumnInfo(name = "student_id")
    val studentId: String,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "book_id")
    var bookId: Int?
)