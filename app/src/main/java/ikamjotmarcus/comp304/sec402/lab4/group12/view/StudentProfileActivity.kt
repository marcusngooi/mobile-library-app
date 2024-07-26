package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModelFactory
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModelFactory

class StudentProfileActivity : AppCompatActivity() {
    private lateinit var studentIdTextView: TextView
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var showBurrowedBookButton: Button
    private lateinit var burrowedBookTitleTextView: TextView
    private lateinit var burrowedBookNameTextView: TextView
    private lateinit var burrowedBookAuthorTextView: TextView
    private lateinit var burrowedBookDescriptionTextView: TextView
    private lateinit var burrowedBookCategoryTextView: TextView

    private lateinit var studentId: String
    private var bookId: Int = -1

    private lateinit var sharedPref: SharedPreferences

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as LibraryApplication).bookRepository)
    }

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as LibraryApplication).studentRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        studentIdTextView = findViewById(R.id.tv_profile_student_id)
        firstNameTextView = findViewById(R.id.tv_profile_first_name)
        lastNameTextView = findViewById(R.id.tv_profile_last_name)
        showBurrowedBookButton = findViewById(R.id.btn_student_profile_show_borrowed_book)
        burrowedBookTitleTextView = findViewById(R.id.tv_profile_burrowed_book_title)
        burrowedBookNameTextView = findViewById(R.id.tv_profile_burrowed_book_name)
        burrowedBookAuthorTextView = findViewById(R.id.tv_profile_burrowed_book_author)
        burrowedBookDescriptionTextView = findViewById(R.id.tv_profile_burrowed_book_description)
        burrowedBookCategoryTextView = findViewById(R.id.tv_profile_burrowed_book_category)

        sharedPref = getSharedPreferences(
            getString(R.string.pref_file_key),
            Context.MODE_PRIVATE
        )

        studentId = sharedPref.getString(
            getString(R.string.pref_student_id),
            getString(R.string.pref_default_string)
        ).toString()

        showBurrowedBookButton.setOnClickListener {
            if (bookId != -1) {
                bookViewModel.getBook(bookId)
            }
            burrowedBookTitleTextView.visibility = View.VISIBLE
            burrowedBookNameTextView.visibility = View.VISIBLE
            burrowedBookAuthorTextView.visibility = View.VISIBLE
            burrowedBookDescriptionTextView.visibility = View.VISIBLE
            burrowedBookCategoryTextView.visibility = View.VISIBLE
        }

        studentViewModel.getStudent(studentId)

        studentViewModel.student.observe(this) { student ->
            studentIdTextView.text = getString(R.string.tv_text_student_profile_student_id, student.studentId)
            firstNameTextView.text = getString(R.string.tv_text_student_profile_first_name, student.firstName)
            lastNameTextView.text = getString(R.string.tv_text_student_profile_last_name, student.lastName)
            if (student.bookId != null) {
                bookId = student.bookId!!
            }
        }

        bookViewModel.book.observe(this) { book ->
            burrowedBookTitleTextView.text = getString(R.string.tv_text_profile_burrowed_book_title)
            burrowedBookNameTextView.text =
                getString(R.string.tv_text_student_profile_book_name, book.bookName)
            burrowedBookAuthorTextView.text =
                getString(R.string.tv_text_student_profile_author_name, book.authorName)
            burrowedBookDescriptionTextView.text = book.bookDescription
            burrowedBookCategoryTextView.text =
                getString(R.string.tv_text_student_profile_category, book.category)
        }
    }
}