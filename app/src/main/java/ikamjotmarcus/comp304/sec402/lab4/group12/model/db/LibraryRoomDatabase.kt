package ikamjotmarcus.comp304.sec402.lab4.group12.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Librarian
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Student
import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.BookDao
import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.LibrarianDao
import ikamjotmarcus.comp304.sec402.lab4.group12.model.dao.StudentDao

@Database(
    entities = [Book::class, Student::class, Librarian::class],
    version = 1,
    exportSchema = false
)
abstract class LibraryRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun studentDao(): StudentDao
    abstract fun librarianDao(): LibrarianDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryRoomDatabase? = null

        fun getDatabase(context: Context): LibraryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryRoomDatabase::class.java,
                    "library_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun addSomeDummyData() {
        val studentDao = studentDao()
        studentDao.insert(Student("1", "Marcus", "Ngooi", "password", null))
        studentDao.insert(Student("2", "Ikamjot", "Hundal", "password", null))

        val bookDao = bookDao()
        bookDao.insert(
            Book(
                null,
                "Harry Potter and the Philosopher's Stone",
                "J.K. Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry.",
                "Fiction",
                3
            )
        )
        bookDao.insert(
            Book(
                null,
                "Harry Potter and the Chamber of Secrets",
                "J.K. Rowling",
                "The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, during which a series of messages on the walls of the school's corridors warn that the \"Chamber of Secrets\" has been opened and that the \"heir of Slytherin\" would kill all pupils who do not come from all-magical families.",
                "Fiction",
                2
            )
        )
        bookDao.insert(
            Book(
                null,
                "The Sixth Extinction",
                "Elizabeth Kolbert",
                "An engrossing account of the looming catastrophe caused by ecology’s “neighbours from hell” – mankind.",
                "Non-Fiction",
                4
            )
        )
        bookDao.insert(
            Book(
                null,
                "The Year of Magical Thinking",
                "Joan Didion",
                "This steely and devastating examination of the author’s grief following the sudden death of her husband changed the nature of writing about bereavement.",
                "Non-Fiction",
                8
            )
        )
        bookDao.insert(
            Book(
                null,
                "No Logo",
                "Naomi Klein",
                "Naomi Klein’s timely anti-branding bible combined a fresh approach to corporate hegemony with potent reportage from the dark side of capitalism.",
                "Non-Fiction",
                1
            )
        )
        bookDao.insert(
            Book(
                null,
                "Birthday Letters",
                "Ted Hughes",
                "These passionate, audacious poems addressed to Hughes’s late wife, Sylvia Plath, contribute to the couple’s mythology and are a landmark in English poetry.",
                "Non-Fiction",
                2
            )
        )
        bookDao.insert(
            Book(
                null,
                "Dreams from My Father",
                "Barack Obama",
                "This remarkably candid memoir revealed not only a literary talent, but a force that would change the face of US politics for ever.",
                "Non-Fiction",
                6
            )
        )
        bookDao.insert(
            Book(
                null,
                "Numerical Methods",
                "Erin Catto",
                "Sometimes the mathematical problems we are faced with in game physics are too difficult to solve exactly. Numerical methods provide a set of tools to get approximate solutions to these difficult problems.",
                "Educational",
                1
            )
        )
        bookDao.insert(
            Book(
                null,
                "Creating E-Learning Games with Unity",
                "David Horachek",
                "Develop your own 3D e-learning game using gamification, systems design, and gameplay programming techniques.",
                "Educational",
                1
            )
        )
        bookDao.insert(
            Book(
                null,
                "Game Engine Architecture",
                "Jason Gregory",
                "This book aims to present a complete discussion of the major components that make up a typical commercial game engine.",
                "Educational",
                1
            )
        )
        bookDao.insert(
            Book(
                null,
                "A Study of History",
                "Arnold Toynbee",
                "Arnold Toynbee's A Study of History is his magnum opus. In it he analyses the rise and fall of all 26 of the great world civilizations; whereas, previous historians had mainly concentrated on the West.",
                "History",
                3
            )
        )
        val librarianDao = librarianDao()
        librarianDao.insert(Librarian("001", "Yash", "Sheth", "COMP304"))
        librarianDao.insert(Librarian("007", "James", "Bond", "CasinoRoyale"))
    }
}