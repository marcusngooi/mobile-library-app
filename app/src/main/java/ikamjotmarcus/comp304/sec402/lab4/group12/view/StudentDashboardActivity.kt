package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModelFactory

class StudentDashboardActivity : AppCompatActivity(), BookClickInterface,
    BookClickDeleteInterface {
    lateinit var booksRV: RecyclerView

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as LibraryApplication).bookRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        booksRV = findViewById(R.id.rv_student_books_list)

        booksRV.layoutManager = LinearLayoutManager(this)

        val bookRVAdapter = BookRVAdapter(this, this, this)

        booksRV.adapter = bookRVAdapter

        observeBooksByCategory(getString(R.string.book_category_name_fiction))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_book_categories_student, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, StudentProfileActivity::class.java)
        when (item.itemId) {
            R.id.student_fiction -> observeBooksByCategory(getString(R.string.book_category_name_fiction))
            R.id.student_non_fiction -> observeBooksByCategory(getString(R.string.book_category_name_non_fiction))
            R.id.student_educational -> observeBooksByCategory(getString(R.string.book_category_name_educational))
            R.id.student_history -> observeBooksByCategory(getString(R.string.book_category_name_history))
            R.id.profile -> startActivity(intent)
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this, StudentBorrowBookActivity::class.java)
        intent.putExtra(getString(R.string.intent_extra_name_book_name), book.bookName)
        intent.putExtra(getString(R.string.intent_extra_name_author_name), book.authorName)
        intent.putExtra(
            getString(R.string.intent_extra_name_book_description),
            book.bookDescription
        )
        intent.putExtra(getString(R.string.intent_extra_name_book_id), book.bookId)
        intent.putExtra(getString(R.string.intent_extra_name_category), book.category)
        intent.putExtra(getString(R.string.intent_extra_name_quantity), book.quantity)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(book: Book) {
        //("Not implemented")
    }

    private fun observeBooksByCategory(category: String) {
        val bookRVAdapter = BookRVAdapter(this, this, this)
        booksRV.adapter = bookRVAdapter
        bookViewModel.getAlphabetizedBooksByCategory(category).observe(this, Observer { list ->
            list?.let {
                bookRVAdapter.updateList(it)
            }
        })
    }


}

