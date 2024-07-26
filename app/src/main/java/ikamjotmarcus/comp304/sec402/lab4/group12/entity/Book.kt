package ikamjotmarcus.comp304.sec402.lab4.group12.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
class Book(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "book_id")
    val bookId: Int?,

    @ColumnInfo(name = "book_name")
    var bookName: String,

    @ColumnInfo(name = "author_name")
    var authorName: String,

    @ColumnInfo(name = "book_description")
    var bookDescription: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int,
)