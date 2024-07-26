package ikamjotmarcus.comp304.sec402.lab4.group12.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Student
import ikamjotmarcus.comp304.sec402.lab4.group12.model.StudentRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {
    val isSignedIn = MutableLiveData<Boolean>()
    val student = MutableLiveData<Student>()

    fun getStudent(studentId: String) = viewModelScope.launch {
        student.value = repository.getStudent(studentId)
    }

    fun signIn(studentId: String, inputtedPassword: String) = viewModelScope.launch {
        val studentPassword = repository.getStudentPassword(studentId)

        isSignedIn.value = inputtedPassword == studentPassword
    }
    fun signUp(studentId: String, firstName: String, lastName: String, password: String) {
        viewModelScope.launch {
            repository.signUp(Student(studentId, firstName, lastName, password, null))
        }
    }

    fun updateStudentBookId(studentId: String, newBookId: Int) = viewModelScope.launch {
        repository.updateStudentBookId(studentId, newBookId)
    }

    suspend fun isStudentExist(studentId: String): Boolean {
        return repository.isStudentExist(studentId)
    }
}