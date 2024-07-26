package ikamjotmarcus.comp304.sec402.lab4.group12.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ikamjotmarcus.comp304.sec402.lab4.group12.model.LibrarianRepository
import kotlinx.coroutines.launch

class LibrarianViewModel(private val repository: LibrarianRepository) : ViewModel() {
    val isSignedIn = MutableLiveData<Boolean>()
    fun signIn(librarianId: String, inputtedPassword: String) = viewModelScope.launch {
        val librarianPassword = repository.getLibrarianPassword(librarianId)
        isSignedIn.value = inputtedPassword == librarianPassword
    }
}