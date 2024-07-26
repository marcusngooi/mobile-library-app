package ikamjotmarcus.comp304.sec402.lab4.group12.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ikamjotmarcus.comp304.sec402.lab4.group12.model.LibrarianRepository

class LibrarianViewModelFactory(private val repository: LibrarianRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibrarianViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LibrarianViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}