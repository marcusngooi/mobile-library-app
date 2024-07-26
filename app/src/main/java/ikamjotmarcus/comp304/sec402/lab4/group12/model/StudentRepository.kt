package ikamjotmarcus.comp304.sec402.lab4.group12.model


import android.util.Log
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Student
import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
     suspend fun getStudentPassword(studentId: String) : String {
        return studentDao.getStudentPassword(studentId)
    }

    suspend fun getStudent(studentId: String): Student {
        return studentDao.getStudent(studentId)
    }

    suspend fun signUp(student: Student) {
        studentDao.insert(student)
    }

    suspend fun updateStudentBookId(studentId: String, newBookId: Int) {
        studentDao.updateStudentBookId(studentId, newBookId)
    }

     suspend fun isStudentExist(studentId: String): Boolean {
        return studentDao.getStudentByNameAndStudentId(studentId) != null
    }
}