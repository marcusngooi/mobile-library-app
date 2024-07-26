package ikamjotmarcus.comp304.sec402.lab4.group12

import android.app.Application
import android.util.Log
import ikamjotmarcus.comp304.sec402.lab4.group12.model.BookRepository
import ikamjotmarcus.comp304.sec402.lab4.group12.model.LibrarianRepository
import ikamjotmarcus.comp304.sec402.lab4.group12.model.StudentRepository
import ikamjotmarcus.comp304.sec402.lab4.group12.model.db.LibraryRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibraryApplication : Application() {
    private val database by lazy { LibraryRoomDatabase.getDatabase(this) }
    val studentRepository by lazy { StudentRepository(database.studentDao()) }
    val bookRepository by lazy { BookRepository(database.bookDao()) }
    val librarianRepository by lazy { LibrarianRepository(database.librarianDao()) }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            database.addSomeDummyData()
            Log.d("something is here", "there")
        }
    }
}