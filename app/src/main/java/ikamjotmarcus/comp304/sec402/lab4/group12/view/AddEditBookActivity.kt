package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.BookViewModelFactory

class AddEditBookActivity : AppCompatActivity() {
    private lateinit var bookNameEditText: EditText
    private lateinit var authorNameEditText: EditText
    private lateinit var bookDescriptionEditText: EditText
    private lateinit var categoryOptionsRadioGroup: RadioGroup
    private lateinit var quantityEditText: EditText
    private lateinit var saveButton: Button

    private var checkedOption = ""

    private var bookId = -1
    private var defaultBookIdValue = -1
    private var defaultQuantityValue = -2

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as LibraryApplication).bookRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_book)

        bookNameEditText = findViewById(R.id.et_book_name)
        authorNameEditText = findViewById(R.id.et_author_name)
        bookDescriptionEditText = findViewById(R.id.et_book_description)
        categoryOptionsRadioGroup = findViewById(R.id.rg_category_options)
        quantityEditText = findViewById(R.id.et_quantity)
        saveButton = findViewById(R.id.btn_update)

        val bookType = intent.getStringExtra(getString(R.string.intent_extra_name_book_type))
        if (bookType.equals(getString(R.string.add_edit_book_type_edit))) {
            val bookName = intent.getStringExtra(getString(R.string.intent_extra_name_book_name))
            val authorName =
                intent.getStringExtra(getString(R.string.intent_extra_name_author_name))
            val bookDescription =
                intent.getStringExtra(getString(R.string.intent_extra_name_book_description))
            val category = intent.getStringExtra(getString(R.string.intent_extra_name_category))
            val quantity = intent.getIntExtra(
                getString(R.string.intent_extra_name_quantity),
                defaultQuantityValue
            )
            bookId = intent.getIntExtra(
                getString(R.string.intent_extra_name_book_id),
                defaultBookIdValue
            )
            saveButton.text = getString(R.string.btn_text_save_update_book)
            bookNameEditText.setText(bookName)
            authorNameEditText.setText(authorName)
            bookDescriptionEditText.setText(bookDescription)
            for (i in 0 until categoryOptionsRadioGroup.childCount) {
                val radioButton = categoryOptionsRadioGroup.getChildAt(i) as RadioButton
                if (radioButton.text == category) {
                    radioButton.isChecked = true
                    checkedOption = radioButton.text.toString()
                }
            }
            quantityEditText.setText(quantity.toString())

        } else {
            saveButton.text = getString(R.string.btn_text_save_save_book)
        }

        categoryOptionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val checkedRadioButton = findViewById<RadioButton>(checkedId)
            checkedOption = checkedRadioButton.text.toString()
        }

        saveButton.setOnClickListener {
            val bookName = bookNameEditText.text.toString()
            val authorName = authorNameEditText.text.toString()
            val bookDescription = bookDescriptionEditText.text.toString()
            val category = checkedOption
            val quantity = quantityEditText.text.toString().toInt()
            val bookId = intent.getIntExtra(
                getString(R.string.intent_extra_name_book_id),
                defaultBookIdValue
            )

            if (bookType.equals(getString(R.string.add_edit_book_type_edit))) {
                if (bookName.isNotEmpty() && authorName.isNotEmpty() && bookDescription.isNotEmpty()) {
                    if (bookId != -1 && quantity != -2) {
                        val updatedBook =
                            Book(bookId, bookName, authorName, bookDescription, category, quantity)
                        bookViewModel.update(updatedBook)
                        Toast.makeText(
                            this,
                            getString(R.string.toast_text_book_updated),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.i(
                            getString(R.string.log_tag_add_edit_book_activity),
                            getString(R.string.log_msg_invalid_id_or_quantity)
                        )
                    }
                }
            } else {
                if (bookName.isNotEmpty() && authorName.isNotEmpty() && bookDescription.isNotEmpty()) {
                    bookViewModel.insert(
                        Book(
                            null,
                            bookName,
                            authorName,
                            bookDescription,
                            category,
                            quantity
                        )
                    )
                    Toast.makeText(
                        this,
                        getString(R.string.toast_text_book_added),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            startActivity(Intent(applicationContext, LibrarianDashboardActivity::class.java))
            this.finish()
        }
    }
}