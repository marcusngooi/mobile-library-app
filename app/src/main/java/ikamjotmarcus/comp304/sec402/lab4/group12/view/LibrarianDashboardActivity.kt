package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModelFactory

class LibrarianDashboardActivity : AppCompatActivity(), BookClickInterface,
    BookClickDeleteInterface {
    private lateinit var booksRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as LibraryApplication).bookRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_librarian_dashboard)

        booksRV = findViewById(R.id.rv_librarian_books_list)
        addFAB = findViewById(R.id.fab_librarian_add_book)

        booksRV.layoutManager = LinearLayoutManager(this)

        val bookRVAdapter = BookRVAdapter(this, this, this)

        booksRV.adapter = bookRVAdapter

        addFAB.setOnClickListener {
            val intent = Intent(this, AddEditBookActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        observeBooksByCategory(getString(R.string.book_category_name_fiction))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_book_categories_librarian, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.librarian_fiction -> observeBooksByCategory(getString(R.string.book_category_name_fiction))
            R.id.librarian_non_fiction -> observeBooksByCategory(getString(R.string.book_category_name_non_fiction))
            R.id.librarian_educational -> observeBooksByCategory(getString(R.string.book_category_name_educational))
            R.id.librarian_history -> observeBooksByCategory(getString(R.string.book_category_name_history))
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this, AddEditBookActivity::class.java)
        intent.putExtra(getString(R.string.intent_extra_name_book_type), getString(R.string.add_edit_book_type_edit))
        intent.putExtra(getString(R.string.intent_extra_name_book_name), book.bookName)
        intent.putExtra(getString(R.string.intent_extra_name_author_name), book.authorName)
        intent.putExtra(getString(R.string.intent_extra_name_book_description), book.bookDescription)
        intent.putExtra(getString(R.string.intent_extra_name_book_id), book.bookId)
        intent.putExtra(getString(R.string.intent_extra_name_category), book.category)
        intent.putExtra(getString(R.string.intent_extra_name_quantity), book.quantity)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(book: Book) {
        bookViewModel.delete(book)
        Toast.makeText(this, getString(R.string.toast_text_book_deleted, book.bookName), Toast.LENGTH_SHORT).show()
    }

    private fun observeBooksByCategory(category: String) {
        val bookRVAdapter = BookRVAdapter(this, this, this)
        booksRV.adapter = bookRVAdapter
        bookViewModel.getAlphabetizedBooksByCategory(category).observe(this) { list ->
            list?.let {
                bookRVAdapter.updateList(it)
            }
        }
    }
}