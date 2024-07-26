package ikamjotmarcus.comp304.sec402.lab4.group12.viewModel

import androidx.lifecycle.*
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.model.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book
    fun getAlphabetizedBooksByCategory(category: String): LiveData<List<Book>> {
        return repository.getAlphabetizedBooksByCategory(category).asLiveData()
    }

    fun getBook(bookId: Int) = viewModelScope.launch {
        _book.value = repository.getBook(bookId)
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }

    fun update(book: Book) = viewModelScope.launch {
        repository.update(book)
    }

    fun delete(book: Book) = viewModelScope.launch {
        repository.delete(book)
    }
}