package ikamjotmarcus.comp304.sec402.lab4.group12.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "librarian_table")
class Librarian(
    @PrimaryKey @ColumnInfo(name = "librarian_id")
    val librarianId: String,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "password")
    var password: String
)