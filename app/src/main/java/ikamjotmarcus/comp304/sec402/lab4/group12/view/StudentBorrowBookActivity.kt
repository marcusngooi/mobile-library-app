package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModelFactory
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModelFactory

class StudentBorrowBookActivity : AppCompatActivity() {
    private lateinit var borrowButton: Button
    private lateinit var sharedPref: SharedPreferences

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as LibraryApplication).bookRepository)
    }

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as LibraryApplication).studentRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_borrow_book)

        val bookName =
            intent.getStringExtra(getString(R.string.intent_extra_name_book_name)).toString()
        val authorName =
            intent.getStringExtra(getString(R.string.intent_extra_name_author_name)).toString()
        val bookDescription =
            intent.getStringExtra(getString(R.string.intent_extra_name_book_description)).toString()
        val category =
            intent.getStringExtra(getString(R.string.intent_extra_name_category)).toString()
        var quantity = intent.getIntExtra(getString(R.string.intent_extra_name_quantity), -1)
        val bookId = intent.getIntExtra(getString(R.string.intent_extra_name_book_id), -1)

        val bookNameText = findViewById<TextView>(R.id.tv_student_book_borrow_book_name)
        val authorNameText = findViewById<TextView>(R.id.tv_student_book_borrow_author_name)
        val bookDescriptionTV = findViewById<TextView>(R.id.tv_student_book_borrow_book_description)
        val quantityBook = findViewById<TextView>(R.id.tv_student_book_borrow_book_quantity)

        bookNameText.text = bookName
        authorNameText.text = authorName
        bookDescriptionTV.text = bookDescription
        quantityBook.text = quantity.toString()

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE)
        val studentId = sharedPref.getString(
            getString(R.string.pref_student_id),
            getString(R.string.pref_default_string)
        ).toString()

        Toast.makeText(this, "$quantity", Toast.LENGTH_SHORT).show()
        borrowButton = findViewById(R.id.btn_borrow)

        borrowButton.setOnClickListener {
            if (quantity > 0) {
                quantity -= 1
                quantityBook.text = quantity.toString()
                val updatedBookQuantity =
                    Book(bookId, bookName, authorName, bookDescription, category, quantity)
                bookViewModel.update(updatedBookQuantity)

                studentViewModel.updateStudentBookId(studentId, bookId)
                Toast.makeText(
                    this,
                    "Book Quantity Decreased to $quantity",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_text_out_of_stock), Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, StudentDashboardActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}